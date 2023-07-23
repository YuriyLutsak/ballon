package com.balloon_spring_jpa.balloon.rest;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.entity.FoilBalloonQuantityInOrder;
import com.balloon_spring_jpa.balloon.repository.FoilBalloonQuantityInOrderRepository;
import com.balloon_spring_jpa.balloon.service.foilBalloon.FoilBalloonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/foil")
public class FoilBalloonRestController{

    private final FoilBalloonServiceImpl foilBalloonService;

    private final FoilBalloonQuantityInOrderRepository foilBalloonQuantityInOrderRepository;

    @GetMapping("/balloons")
    public List<FoilBalloonDTO> findAll() {
        return foilBalloonService.findAll();
    }

    @PostMapping("/balloons")
    public FoilBalloonDTO save(@RequestBody FoilBalloonDTO foilBalloon) {
        return foilBalloonService.save(foilBalloon);
    }

    @PutMapping("/balloons/{id}")
    public FoilBalloonDTO update(@RequestBody FoilBalloonDTO foilBalloon,@PathVariable UUID id) {
        return foilBalloonService.update(foilBalloon, id);
    }

    @GetMapping("/balloons/{id}")
    public FoilBalloonDTO findById(@PathVariable UUID id) {
        return foilBalloonService.findById(id);
    }

    @DeleteMapping("/balloons/{id}")
    public void delete(@PathVariable UUID id) {
        foilBalloonService.delete(id);
    }

    @PostMapping("/balloons/quantity")
    public FoilBalloonQuantityInOrder saveQuantity(@RequestBody FoilBalloonQuantityInOrder quantity) {
        return foilBalloonQuantityInOrderRepository.save(quantity);
    }
}
