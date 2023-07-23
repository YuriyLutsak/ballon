package com.balloon_spring_jpa.balloon.service.foilBalloon;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.FoilBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.dto.mapper.FoilBalloonMapper;
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
        var entitiesFromDB = foilBalloonRepository.findAll();
        return foilBalloonMapper.mapToListFoilBalloonDTO(entitiesFromDB);
    }

    @Transactional
    @Override
    public FoilBalloonDTO findById(UUID id) {
        var entityById = foilBalloonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no such entity"));
        return foilBalloonMapper.mapToFoilBalloonDTO(entityById);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        var byId = foilBalloonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no such entity"));
        foilBalloonRepository.delete(byId);
    }

    @Transactional
    @Override
    public FoilBalloonDTO update(FoilBalloonDTO balloon, UUID id) {
        foilBalloonRepository.findById(id).orElseThrow();
        var toEntity = foilBalloonMapper.mapToFoilBalloonEntity(balloon);
        toEntity.setId(id);
        var savingEntity = foilBalloonRepository.save(toEntity);
        return foilBalloonMapper.mapToFoilBalloonDTO(savingEntity);
    }

    @Override
    @Transactional
    public BigDecimal removeFromBalanceAndCountPrice(List<FoilBalloonQuantityInOrderDTO> quantityInOrderList) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int quantityFromListDTO;
        FoilBalloonDTO balloonFromListDTO;

        for (FoilBalloonQuantityInOrderDTO inOrderDTO : quantityInOrderList) {
            balloonFromListDTO = inOrderDTO.getFoilBalloon();
            quantityFromListDTO = inOrderDTO.getQuantity();

            var balloonFromDB = foilBalloonRepository.findById(balloonFromListDTO.getId()).orElseThrow();

            int result = balloonFromDB.getStockBalance() - quantityFromListDTO;

            if (result < 0) {
                throw new RuntimeException("Quantity of foil balloons in your order bigger then in stock balance");
            }

            totalPrice = totalPrice.add(balloonFromDB.getCost().multiply(BigDecimal.valueOf(quantityFromListDTO)));

            balloonFromDB.setStockBalance(result);
            balloonFromListDTO.setStockBalance(result);
            balloonFromListDTO.setCost(balloonFromDB.getCost());
            balloonFromListDTO.setSize(balloonFromDB.getSize());
            balloonFromListDTO.setFoilBalloonType(balloonFromDB.getFoilBalloonType());
        }

        return totalPrice;
    }
}
