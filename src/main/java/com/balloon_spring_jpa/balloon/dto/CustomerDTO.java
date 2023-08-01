package com.balloon_spring_jpa.balloon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    private UUID id;
    private String name;
    private long phoneNumber;
    private BigDecimal totalBalance = BigDecimal.ZERO;
    private List<OrderDTO> orders;
}
