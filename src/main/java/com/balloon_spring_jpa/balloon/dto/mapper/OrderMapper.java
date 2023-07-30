package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.OrderDTO;
import com.balloon_spring_jpa.balloon.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LatexBalloonQuantityInOrderMapper.class, FoilBalloonQuantityInOrderMapper.class, CustomerMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order mapToOrderEntity(OrderDTO orderDTO);

    @Mapping(target = "customer", qualifiedByName = {"CustomerMapper", "mapToCustomerDTO"})
    @Mapping(target = "latexBalloonQuantity",
            qualifiedByName = {"LatexBalloonQuantityInOrderMapper", "mapToQuantityInOrderDTOListWithoutOrder"})
    @Mapping(target = "foilBalloonQuantity",
    qualifiedByName = {"FoilBalloonQuantityInOrderMapper", "mapToQuantityInOrderDTOListWithoutOrder"})
    OrderDTO mapToOrderDTO(Order order);

    List<OrderDTO> mapToOrderDTOList(List<Order> orderList);
}
