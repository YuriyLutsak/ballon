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
    LatexBalloon mapToLatexBalloonEntity(LatexBalloonDTO latexBalloon);

    @Named("mapToLatexBalloonWithoutQuantityInOrder")
    @Mapping(target = "latexBalloonQuantityInOrder", ignore = true)
    LatexBalloonDTO mapToLatexBalloonWithoutQuantityInOrder(LatexBalloon latexBalloonEntity);

    @Mapping(target = "latexBalloonQuantityInOrder", ignore = true)
    LatexBalloonDTO mapToLatexBalloonDTO(LatexBalloon latexBalloonEntity);

    @Mapping(target = "latexBalloonQuantityInOrder",ignore = true)
    List<LatexBalloonDTO> mapToLatexBalloonListDTO(List<LatexBalloon> latexBalloonEntities);

}
