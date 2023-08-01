package com.balloon_spring_jpa.balloon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LatexBalloonQuantityInOrderDTO {

    private UUID id;
    private int quantity;
    private LatexBalloonDTO latexBalloon;
    private OrderDTO order;
}
