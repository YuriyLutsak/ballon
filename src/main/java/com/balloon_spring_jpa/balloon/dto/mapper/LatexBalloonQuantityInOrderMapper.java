package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.LatexBalloonQuantityInOrderDTO;
import com.balloon_spring_jpa.balloon.entity.LatexBalloonQuantityInOrder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("LatexBalloonQuantityInOrderMapper")
@Mapper(componentModel = "spring", uses = {LatexBalloonMapper.class})
public interface LatexBalloonQuantityInOrderMapper {

    @Mapping(target = "id", ignore = true)
    LatexBalloonQuantityInOrder mapToQuantityInOrderEntity(LatexBalloonQuantityInOrderDTO quantityInOrder);

    @Named("mapToQuantityInOrderDTOListWithoutOrder")
    @IterableMapping(qualifiedByName = "mapToQuantityInOrderDTOWithoutOrder")
    List<LatexBalloonQuantityInOrderDTO> mapToQuantityInOrderDTOListWithoutOrder(List<LatexBalloonQuantityInOrder> quantityInOrders);

    @Mapping(target = "order",ignore = true)
    List<LatexBalloonQuantityInOrderDTO> mapToQuantityInOrderDTOList(List<LatexBalloonQuantityInOrder> quantityInOrders);

    @Named("mapToQuantityInOrderDTOWithoutOrder")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "latexBalloon", qualifiedByName ={"LatexBalloonMapper", "mapToLatexBalloonWithoutQuantityInOrder"})
    LatexBalloonQuantityInOrderDTO mapToQuantityInOrderDTOWithoutOrder(LatexBalloonQuantityInOrder quantityInOrder);

    @Mapping(target = "order", ignore = true)
    LatexBalloonQuantityInOrderDTO mapToQuantityInOrderDTO(LatexBalloonQuantityInOrder quantityInOrder);


}
