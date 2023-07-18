package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.OrderDTO;
import com.balloon_spring_jpa.balloon.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LatexBalloonQuantityInOrderMapper.class, FoilBalloonQuantityInOrderMapper.class, CustomerMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order mapToOrderEntity(OrderDTO order);

    @Mapping(target = "customer", qualifiedByName = {"CustomerMapper", "mapToCustomerDTO"})
    @Mapping(target = "latexBalloonQuantity",
            qualifiedByName = {"LatexBalloonQuantityInOrderMapper", "mapToQuantityInOrderDTOListWithoutOrder"})
    @Mapping(target = "foilBalloonQuantity",
    qualifiedByName = {"FoilBalloonQuantityInOrderMapper", "mapToQuantityInOrderDTOListWithoutOrder"})
    OrderDTO mapToOrderDTO(Order orderEntity);

    List<OrderDTO> mapToOrderDTOList(List<Order> orderEntities);























//    @Mapping(target = "latexBalloonQuantity.order", ignore = true)
//    @Mapping(target = "latexBalloonQuantity.latexBalloon", ignore = true)

//    @Mapping(target = "LatexBalloonQuantityInOrder.latexBalloon", constant = "null")
//    @Mapping(target = "LatexBalloonQuantityInOrder.order", constant = "null")
//    @Mapping(target = "LatexBalloon.latexBalloonQuantityInOrder",constant = "null")
//    @Mapping(target = "Customer.orders", constant = "null")
//    @Mapping(target = "Order.customer", constant = "null")
//    @Mapping(target = "Order.latexBalloonQuantity", constant = "null")


    //    @Mapping(target = "Order.foilBalloonQuantity", ignore = true)
//
//
//
//    @Mapping(target = "FoilBalloonQuantityInOrder.foilBalloon", ignore = true)
//    @Mapping(target = "FoilBalloonQuantityInOrder.order", ignore = true)

    //OrderMapperImpl.latexBalloonQuantityInOrderListToLatexBalloonQuantityInOrderDTOList
    //OrderMapperImpl.latexBalloonQuantityInOrderToLatexBalloonQuantityInOrderDTO
    //OrderMapperImpl.latexBalloonQuantityInOrderListToLatexBalloonQuantityInOrderDTOList
    //OrderMapperImpl.latexBalloonQuantityInOrderToLatexBalloonQuantityInOrderDTO
    //customerToCustomerDTO
    //OrderMapperImpl.mapToOrderDTO

    //latexBalloonToLatexBalloonDTO

//    @Mapping(target = "LatexBalloonQuantityInOrder.latexBalloon", ignore = true)
//    @Mapping(target = "LatexBalloonQuantityInOrder.order", ignore = true)
//    @Mapping(target = "LatexBalloon.latexBalloonQuantityInOrder", ignore = true)
//    @Mapping(target = "Customer.orders", ignore = true)


    //    @Mapping(target = "LatexBalloonQuantityInOrder.latexBalloon", ignore = true)
//    @Mapping(target = "LatexBalloonQuantityInOrder.order", ignore = true)
//    @Mapping(target = "LatexBalloon.latexBalloonQuantityInOrder", ignore = true)
//    @Mapping(target = "Customer.orders", ignore = true)
//    @Mapping(target = "Order.customer", ignore = true)
//    @Mapping(target = "Order.latexBalloonQuantity", ignore = true)
//@Mapping(target = "LatexBalloonQuantityInOrder.latexBalloon", constant = "null")
//@Mapping(target = "LatexBalloonQuantityInOrder.order", constant = "null")
//@Mapping(target = "LatexBalloon.latexBalloonQuantityInOrder",constant = "null")
//@Mapping(target = "Customer.orders", constant = "null")
//@Mapping(target = "Order.customer", constant = "null")
//@Mapping(target = "Order.latexBalloonQuantity", constant = "null")


//    @Mapping(target = "man.name", source = "lastNaem")
//    значение из поля "lastNaem" будет отображено и установлено в поле "name" внутри объекта "man".
}
