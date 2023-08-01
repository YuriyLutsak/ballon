package com.balloon_spring_jpa.balloon.entity;

import com.balloon_spring_jpa.balloon.balloonEnum.LatexBalloonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "latex_balloon")
public class LatexBalloon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "balloonType")
    @Enumerated(EnumType.STRING)
    private LatexBalloonType balloonType;

    @Column(name = "size")
    private int size;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "stock_balance")
    private int stockBalance;

    @Column(name = "is_glued")
    private boolean isGlued;

    @JsonIgnore
    @OneToMany(mappedBy = "latexBalloon", fetch = FetchType.LAZY)
    private List<LatexBalloonQuantityInOrder> latexBalloonQuantityInOrders;
}
