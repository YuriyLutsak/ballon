package com.balloon_spring_jpa.balloon.service.latexBalloon;

import com.balloon_spring_jpa.balloon.dto.LatexBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.LatexBalloonQuantityInOrderDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public interface LatexBalloonService {

    List<LatexBalloonDTO> findAll();

    BigDecimal removeFromBalanceAndCountPrice(List<LatexBalloonQuantityInOrderDTO> quantityInOrders);

    LatexBalloonDTO save(LatexBalloonDTO latexBalloon);

    LatexBalloonDTO update(LatexBalloonDTO latexBalloon, UUID id);

    LatexBalloonDTO findById(UUID id);

    void delete(UUID id);
}
