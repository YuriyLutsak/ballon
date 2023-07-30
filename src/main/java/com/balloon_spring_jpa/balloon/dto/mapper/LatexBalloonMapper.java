package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.LatexBalloonDTO;
import com.balloon_spring_jpa.balloon.entity.LatexBalloon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("LatexBalloonMapper")
@Mapper(componentModel = "spring")
public interface LatexBalloonMapper {

    @Mapping(target = "id", ignore = true)
    LatexBalloon mapToLatexBalloonEntity(LatexBalloonDTO latexBalloonDTO);

    @Named("mapToLatexBalloonWithoutQuantityInOrder")
    @Mapping(target = "latexBalloonQuantityInOrders", ignore = true)
    LatexBalloonDTO mapToLatexBalloonWithoutQuantityInOrder(LatexBalloon latexBalloon);

    @Mapping(target = "latexBalloonQuantityInOrders", ignore = true)
    LatexBalloonDTO mapToLatexBalloonDTO(LatexBalloon latexBalloon);

    @Mapping(target = "latexBalloonQuantityInOrders",ignore = true)
    List<LatexBalloonDTO> mapToLatexBalloonListDTO(List<LatexBalloon> latexBalloonList);
}
