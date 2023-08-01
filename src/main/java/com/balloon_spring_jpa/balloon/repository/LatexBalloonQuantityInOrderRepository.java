package com.balloon_spring_jpa.balloon.repository;

import com.balloon_spring_jpa.balloon.entity.LatexBalloonQuantityInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LatexBalloonQuantityInOrderRepository extends JpaRepository<LatexBalloonQuantityInOrder, UUID> {
}
