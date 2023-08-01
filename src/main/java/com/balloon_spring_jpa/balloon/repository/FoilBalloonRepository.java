package com.balloon_spring_jpa.balloon.repository;

import com.balloon_spring_jpa.balloon.entity.FoilBalloon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoilBalloonRepository extends JpaRepository<FoilBalloon, UUID> {
}