package com.balloon_spring_jpa.balloon.repository;

import com.balloon_spring_jpa.balloon.entity.FoilBalloonQuantityInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoilBalloonQuantityInOrderRepository extends JpaRepository<FoilBalloonQuantityInOrder, UUID> {
}
