package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.entity.FoilBalloon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("FoilBalloonMapper")
@Mapper(componentModel = "spring")
public interface FoilBalloonMapper {

    @Mapping(target = "id", ignore = true)
    FoilBalloon mapToFoilBalloonEntity(FoilBalloonDTO foilBalloon);

    @Named("mapToFoilBalloonDTO")
    @Mapping(target = "foilBalloonQuantityInOrders", ignore = true)
    FoilBalloonDTO mapToFoilBalloonDTO(FoilBalloon foilBalloonEntity);

    @Mapping(target = "foilBalloonQuantityInOrders", ignore = true)
    List<FoilBalloonDTO> mapToListFoilBalloonDTO(List<FoilBalloon> balloonListEntity);
}
