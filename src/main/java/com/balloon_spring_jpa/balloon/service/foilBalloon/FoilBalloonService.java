package com.balloon_spring_jpa.balloon.service.foilBalloon;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.FoilBalloonQuantityInOrderDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface FoilBalloonService {

    List<FoilBalloonDTO> findAll();

    BigDecimal removeFromBalanceAndCountPrice(List<FoilBalloonQuantityInOrderDTO> quantityInOrderList);

    FoilBalloonDTO save(FoilBalloonDTO balloon);

    FoilBalloonDTO update(FoilBalloonDTO balloon, UUID id);

    FoilBalloonDTO updateStockBalance(UUID id, int stockBalance);

    FoilBalloonDTO findById(UUID id);

    void delete(UUID id);
}
