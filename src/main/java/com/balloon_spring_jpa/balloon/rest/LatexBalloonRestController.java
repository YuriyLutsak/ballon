package com.balloon_spring_jpa.balloon.rest;

import com.balloon_spring_jpa.balloon.dto.LatexBalloonDTO;
import com.balloon_spring_jpa.balloon.service.latexBalloon.LatexBalloonServiceImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/latex/balloons")
public class LatexBalloonRestController {

    private final LatexBalloonServiceImpl latexBalloonService;

    @GetMapping("")
    public List<LatexBalloonDTO> findAll() {
        return latexBalloonService.findAll();
    }

    @PostMapping("")
    public LatexBalloonDTO save(@RequestBody LatexBalloonDTO latexBalloon) {
        return latexBalloonService.save(latexBalloon);
    }

    @PutMapping("/{id}")
    public LatexBalloonDTO update(@RequestBody LatexBalloonDTO latexBalloon, @PathVariable UUID id) {
        return latexBalloonService.update(latexBalloon, id);
    }

    @GetMapping("/{id}")
    public LatexBalloonDTO findById(@PathVariable UUID id) {
        return latexBalloonService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        latexBalloonService.delete(id);
    }
}
