package com.balloon_spring_jpa.balloon.dto;

import com.balloon_spring_jpa.balloon.balloonEnum.FoilBalloonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FoilBalloonDTO {

    private UUID id;
    private FoilBalloonType foilBalloonType;
    private int size;
    private BigDecimal cost;
    private int stockBalance;
    private List<FoilBalloonQuantityInOrderDTO> foilBalloonQuantityInOrders;
}
