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

    //target = "foilBalloon" - обращение к ссылке класса

    // поле находиться в этом классе(FoilBalloonQuantityInOrder)
    @Mapping(target = "id", ignore = true)
    //заходим в id  FoilBalloonQuantityInOrder игнорим его в FoilBalloonQuantityInOrder
    FoilBalloonQuantityInOrder mapToQuantityInOrderEntity(FoilBalloonQuantityInOrderDTO quantityInOrder);

    //---------------------------------------------------------------------
    // игнарируемое поле(foilBalloonQuantityInOrders) находиться не в этом классе(FoilBalloonQuantityInOrder),
    // а через класс(FoilBalloon)
    @Mapping(target = "foilBalloon", qualifiedByName = {"FoilBalloonMapper", "mapToFoilBalloonDTO"})
    // заходим в privatе FoilBalloon foilBalloon,
    // пушто у него есть private List<FoilBalloonQuantityInOrderDTO> foilBalloonQuantityInOrders
    // и в его маппере (FoilBalloonMapper) игнорим поле foilBalloonQuantityInOrders
    // с помощу метода mapToFoilBalloonDTO в нем
    @Mapping(target = "order", ignore = true)
    FoilBalloonQuantityInOrderDTO mapToQuantityInOrderDTO(FoilBalloonQuantityInOrder quantityInOrder);

    @Named("mapToQuantityInOrderDTOListWithoutOrder")
    @IterableMapping(qualifiedByName = "mapToQuantityInOrderDTOWithoutOrder")
    List<FoilBalloonQuantityInOrderDTO> mapToQuantityInOrderDTOListWithoutOrder(List<FoilBalloonQuantityInOrder> quantityInOrders);

    @Named("mapToQuantityInOrderDTOWithoutOrder")
    @Mapping(target = "order", ignore = true)
    FoilBalloonQuantityInOrderDTO mapToQuantityInOrderDTOtWithoutOrder(FoilBalloonQuantityInOrder quantityInOrder);

    @Mapping(target = "order", ignore = true)
    List<FoilBalloonQuantityInOrderDTO> mapToQuantityInOrderDTOList(List<FoilBalloonQuantityInOrder> quantityInOrders );
}
