package com.balloon_spring_jpa.balloon.service.latexBalloon;

import com.balloon_spring_jpa.balloon.dto.LatexBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.LatexBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.dto.mapper.LatexBalloonMapper;
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
        var entitiesFromDB = latexBalloonRepository.findAll();
        return latexBalloonMapper.mapToLatexBalloonListDTO(entitiesFromDB);
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
                .orElseThrow(() -> new RuntimeException("no such toEntity"));
        var toEntity = latexBalloonMapper.mapToLatexBalloonEntity(latexBalloon);

        toEntity.setId(id);

        var saveEntity = latexBalloonRepository.save(toEntity);
        return latexBalloonMapper.mapToLatexBalloonDTO(saveEntity);
    }

    @Override
    @Transactional
    public LatexBalloonDTO findById(UUID id) {
        var entity = latexBalloonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no such entity"));
        return latexBalloonMapper.mapToLatexBalloonDTO(entity);
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
            var balloonFromListDTO = inOrder.getLatexBalloon();
            quantity = inOrder.getQuantity();

            var balloonFromDB = latexBalloonRepository.findById(balloonFromListDTO.getId()).orElseThrow();

            int result = balloonFromDB.getStockBalance() - quantity;

            if (result < 0) {
                throw new RuntimeException(
                        "Your order quantity of latex balloon bigger than stock balance");
            }

            var priceOfOrder = balloonFromDB.getCost().multiply(BigDecimal.valueOf(quantity));

            totalPrice = totalPrice.add(priceOfOrder);

            balloonFromDB.setStockBalance(result);
            balloonFromListDTO.setStockBalance(result);
            balloonFromListDTO.setGlue(balloonFromDB.isGlue());
            balloonFromListDTO.setCost(balloonFromDB.getCost());
            balloonFromListDTO.setSize(balloonFromDB.getSize());
            balloonFromListDTO.setBalloonType(balloonFromDB.getBalloonType());
        }
        return totalPrice;
    }
}
