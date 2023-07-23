package com.balloon_spring_jpa.balloon.dto;

import com.balloon_spring_jpa.balloon.balloonEnum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDTO {

    private UUID id;
    private String address;
    private LocalDateTime dateTime;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private CustomerDTO customer;
    private List<LatexBalloonQuantityInOrderDTO> latexBalloonQuantity;
    private List<FoilBalloonQuantityInOrderDTO> foilBalloonQuantity;
}

