package com.balloon_spring_jpa.balloon.entity;

import com.balloon_spring_jpa.balloon.balloonEnum.OrderStatus;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue//(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "address")
    private String address;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "date_time", columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime dateTime;


    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LatexBalloonQuantityInOrder> latexBalloonQuantity;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FoilBalloonQuantityInOrder> foilBalloonQuantity;


    @PrePersist
    public void prePersist() {
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
    }


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "order_foil_balloons",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "foil_balloons_id")
//    )
//    private List<FoilBalloon> foilBalloons;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "order_latex_balloon",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "latex_balloon_id")
//    )
//    private List<LatexBalloon> latexBalloons;
}
