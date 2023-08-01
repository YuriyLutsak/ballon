package com.balloon_spring_jpa.balloon.service.foilBalloon;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.FoilBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.dto.mapper.FoilBalloonMapper;
import com.balloon_spring_jpa.balloon.exception.*;
import com.balloon_spring_jpa.balloon.repository.FoilBalloonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoilBalloonServiceImpl implements FoilBalloonService {

    private final FoilBalloonRepository foilBalloonRepository;
    private final FoilBalloonMapper foilBalloonMapper;

    @Transactional
    @Override
    public FoilBalloonDTO save(FoilBalloonDTO balloon) {
        var toEntity = foilBalloonMapper.mapToFoilBalloonEntity(balloon);
        var saveEntity = foilBalloonRepository.save(toEntity);
        return foilBalloonMapper.mapToFoilBalloonDTO(saveEntity);
    }

    @Override
    public List<FoilBalloonDTO> findAll() {
        var foilBalloonList = foilBalloonRepository.findAll();
        return foilBalloonMapper.mapToListFoilBalloonDTO(foilBalloonList);
    }

    @Transactional
    @Override
    public FoilBalloonDTO findById(UUID id) {
        var foilBalloon = foilBalloonRepository.findById(id)
                .orElseThrow(() -> new FoilBalloonNotFoundedException(id));
        return foilBalloonMapper.mapToFoilBalloonDTO(foilBalloon);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        var foilBalloon = foilBalloonRepository.findById(id)
                .orElseThrow(() -> new FoilBalloonNotFoundedException(id));
        foilBalloonRepository.delete(foilBalloon);
    }

    @Transactional
    @Override
    public FoilBalloonDTO update(FoilBalloonDTO balloon, UUID id) {
        foilBalloonRepository.findById(id).orElseThrow(() -> new FoilBalloonNotFoundedException(id));
        var toEntity = foilBalloonMapper.mapToFoilBalloonEntity(balloon);
        toEntity.setId(id);
        var savingEntity = foilBalloonRepository.save(toEntity);
        return foilBalloonMapper.mapToFoilBalloonDTO(savingEntity);
    }

    @Override
    @Transactional
    public FoilBalloonDTO updateStockBalance(UUID id, int stockBalance) {
        var foilBalloon = foilBalloonRepository.findById(id).orElseThrow(() -> new FoilBalloonNotFoundedException(id));
        int foilBalloonStockBalance = foilBalloon.getStockBalance();

        int result = foilBalloonStockBalance + stockBalance;

        foilBalloon.setId(id);
        foilBalloon.setStockBalance(result);
        var savedEntity = foilBalloonRepository.save(foilBalloon);

        return foilBalloonMapper.mapToFoilBalloonDTO(savedEntity);
    }

    @Override
    @Transactional
    public BigDecimal removeFromBalanceAndCountPrice(List<FoilBalloonQuantityInOrderDTO> quantityInOrderList) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int quantity;
        FoilBalloonDTO foilBalloonDTO;

        for (FoilBalloonQuantityInOrderDTO inOrderDTO : quantityInOrderList) {
            foilBalloonDTO = inOrderDTO.getFoilBalloon();
            quantity = inOrderDTO.getQuantity();

            var foilBalloon = foilBalloonRepository.findById(foilBalloonDTO.getId())
                    .orElseThrow(() -> new FoilBalloonNotFoundedException(inOrderDTO.getFoilBalloon().getId()));

            int result = foilBalloon.getStockBalance() - quantity;

            if (result < 0) {
                throw new NotEnoughStockBalanceException(foilBalloonDTO.getId(), quantity - foilBalloon.getStockBalance());
            }

            totalPrice = totalPrice.add(foilBalloon.getCost().multiply(BigDecimal.valueOf(quantity)));

            foilBalloon.setStockBalance(result);
            foilBalloonDTO.setStockBalance(result);
            foilBalloonDTO.setCost(foilBalloon.getCost());
            foilBalloonDTO.setSize(foilBalloon.getSize());
            foilBalloonDTO.setFoilBalloonType(foilBalloon.getFoilBalloonType());
        }

        return totalPrice;
    }
}
