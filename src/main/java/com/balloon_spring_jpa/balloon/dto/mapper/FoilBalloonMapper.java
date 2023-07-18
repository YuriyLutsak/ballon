package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonDTO;
import com.balloon_spring_jpa.balloon.dto.FoilBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.entity.FoilBalloon;
import com.balloon_spring_jpa.balloon.entity.FoilBalloonQuantityInOrder;
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

    List<FoilBalloonDTO> mapToListFoilBalloonDTO(List<FoilBalloon> balloonListEntity);
}
