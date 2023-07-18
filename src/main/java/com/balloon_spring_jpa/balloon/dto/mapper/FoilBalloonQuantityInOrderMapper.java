package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.FoilBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.entity.FoilBalloonQuantityInOrder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("FoilBalloonQuantityInOrderMapper")
@Mapper(componentModel = "spring", uses = {FoilBalloonMapper.class})
public interface FoilBalloonQuantityInOrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "foilBalloon", qualifiedByName = {"FoilBalloonMapper", "mapToFoilBalloonDTO"})
    FoilBalloonQuantityInOrderDTO mapToQuantityInOrderDTO(FoilBalloonQuantityInOrder quantityInOrder);

    @Named("mapToQuantityInOrderDTOListWithoutOrder")
    @IterableMapping(qualifiedByName = "mapToQuantityInOrderDTOWithoutOrder")
    List<FoilBalloonQuantityInOrderDTO> mapToQuantityInOrderDTOListWithoutOrder(List<FoilBalloonQuantityInOrder> quantityInOrders);

    @Named("mapToQuantityInOrderDTOWithoutOrder")
    @Mapping(target = "order", ignore = true)
    FoilBalloonQuantityInOrderDTO mapToQuantityInOrderDTOtWithoutOrder(FoilBalloonQuantityInOrder quantityInOrder);

    FoilBalloonQuantityInOrder mapToQuantityInOrderEntity(FoilBalloonQuantityInOrderDTO quantityInOrder);
}
