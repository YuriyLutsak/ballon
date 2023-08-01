package com.balloon_spring_jpa.balloon.entity;

import com.balloon_spring_jpa.balloon.balloonEnum.FoilBalloonType;
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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "foil_balloon")
public class FoilBalloon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoilBalloonType foilBalloonType;

    @Column(name = "size")
    private int size;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "stock_balance")
    private int stockBalance;

    @JsonIgnore
    @OneToMany(mappedBy = "foilBalloon", fetch = FetchType.LAZY)
    private List<FoilBalloonQuantityInOrder> foilBalloonQuantityInOrders;
}

