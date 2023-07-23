package com.balloon_spring_jpa.balloon.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "latex_balloon_quantity_in_order")
public class LatexBalloonQuantityInOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private LatexBalloon latexBalloon;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;
}
