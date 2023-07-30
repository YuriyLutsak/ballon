package com.balloon_spring_jpa.balloon.rest;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.service.foilBalloon.FoilBalloonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/foil/balloons")
public class FoilBalloonRestController{

    private final FoilBalloonServiceImpl foilBalloonService;

    @GetMapping("")
    public List<FoilBalloonDTO> findAll() {
        return foilBalloonService.findAll();
    }

    @PostMapping("")
    public FoilBalloonDTO save(@RequestBody FoilBalloonDTO foilBalloon) {
        return foilBalloonService.save(foilBalloon);
    }

    @PutMapping("/{id}")
    public FoilBalloonDTO update(@RequestBody FoilBalloonDTO foilBalloon,@PathVariable UUID id) {
        return foilBalloonService.update(foilBalloon, id);
    }

    @PatchMapping("/{id}/{stockBalance}")
    public FoilBalloonDTO updateStockBalance(@PathVariable UUID id, @PathVariable int stockBalance) {
        return foilBalloonService.updateStockBalance(id, stockBalance);
    }

    @GetMapping("/{id}")
    public FoilBalloonDTO findById(@PathVariable UUID id) {
        return foilBalloonService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        foilBalloonService.delete(id);
    }
}
