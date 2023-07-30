package com.balloon_spring_jpa.balloon.service.latexBalloon;

import com.balloon_spring_jpa.balloon.dto.LatexBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.LatexBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.dto.mapper.LatexBalloonMapper;
import com.balloon_spring_jpa.balloon.exception.*;
import com.balloon_spring_jpa.balloon.repository.LatexBalloonRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LatexBalloonServiceImpl implements LatexBalloonService {

    private final LatexBalloonRepository latexBalloonRepository;
    private final LatexBalloonMapper latexBalloonMapper;

    @Override
    @Transactional
    public List<LatexBalloonDTO> findAll() {
        var latexBalloonList = latexBalloonRepository.findAll();
        return latexBalloonMapper.mapToLatexBalloonListDTO(latexBalloonList);
    }

    @Override
    @Transactional
    public LatexBalloonDTO save(LatexBalloonDTO latexBalloon) {
        var toEntity = latexBalloonMapper.mapToLatexBalloonEntity(latexBalloon);
        var saveEntity = latexBalloonRepository.save(toEntity);
        return latexBalloonMapper.mapToLatexBalloonDTO(saveEntity);
    }

    @Override
    @Transactional
    public LatexBalloonDTO update(LatexBalloonDTO latexBalloon, UUID id) {
        latexBalloonRepository.findById(id)
                .orElseThrow(() -> new LatexBalloonException(id));
        var toEntity = latexBalloonMapper.mapToLatexBalloonEntity(latexBalloon);

        toEntity.setId(id);

        var saveEntity = latexBalloonRepository.save(toEntity);
        return latexBalloonMapper.mapToLatexBalloonDTO(saveEntity);
    }

    @Override
    @Transactional
    public LatexBalloonDTO findById(UUID id) {
        var latexBalloon = latexBalloonRepository.findById(id)
                .orElseThrow(() -> new LatexBalloonException(id));
        return latexBalloonMapper.mapToLatexBalloonDTO(latexBalloon);
    }

    @Override
    public void delete(UUID id) {
        latexBalloonRepository.deleteById(id);
    }

    @Override
    public BigDecimal removeFromBalanceAndCountPrice(List<LatexBalloonQuantityInOrderDTO> quantityInOrders) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int quantity;

        for (LatexBalloonQuantityInOrderDTO inOrder : quantityInOrders) {
            var latexBalloonDTO = inOrder.getLatexBalloon();
            quantity = inOrder.getQuantity();

            var latexBalloon = latexBalloonRepository.findById(latexBalloonDTO.getId())
                    .orElseThrow(() -> new LatexBalloonException(inOrder.getLatexBalloon().getId()));

            int result = latexBalloon.getStockBalance() - quantity;

            if (result < 0) {
                throw new StockBalanceException(quantity - latexBalloon.getStockBalance());
            }

            var priceOfOrder = latexBalloon.getCost().multiply(BigDecimal.valueOf(quantity));

            totalPrice = totalPrice.add(priceOfOrder);

            latexBalloon.setStockBalance(result);
            latexBalloonDTO.setStockBalance(result);
            latexBalloonDTO.setGlued(latexBalloon.isGlued());
            latexBalloonDTO.setCost(latexBalloon.getCost());
            latexBalloonDTO.setSize(latexBalloon.getSize());
            latexBalloonDTO.setBalloonType(latexBalloon.getBalloonType());
        }
        return totalPrice;
    }
}
