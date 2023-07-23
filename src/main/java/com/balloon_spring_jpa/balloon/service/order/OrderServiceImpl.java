package com.balloon_spring_jpa.balloon.service.order;

import com.balloon_spring_jpa.balloon.balloonEnum.OrderStatus;
import com.balloon_spring_jpa.balloon.dto.OrderDTO;
import com.balloon_spring_jpa.balloon.dto.mapper.CustomerMapper;
import com.balloon_spring_jpa.balloon.dto.mapper.FoilBalloonMapper;
import com.balloon_spring_jpa.balloon.dto.mapper.LatexBalloonMapper;
import com.balloon_spring_jpa.balloon.dto.mapper.OrderMapper;
import com.balloon_spring_jpa.balloon.entity.Customer;
import com.balloon_spring_jpa.balloon.entity.FoilBalloonQuantityInOrder;
import com.balloon_spring_jpa.balloon.entity.LatexBalloonQuantityInOrder;
import com.balloon_spring_jpa.balloon.entity.Order;
import com.balloon_spring_jpa.balloon.repository.OrderRepository;
import com.balloon_spring_jpa.balloon.service.customer.CustomerService;
import com.balloon_spring_jpa.balloon.service.foilBalloon.FoilBalloonService;
import com.balloon_spring_jpa.balloon.service.latexBalloon.LatexBalloonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final FoilBalloonService foilBalloonService;
    private final LatexBalloonService latexBalloonService;
    private final CustomerService customerService;

    @Transactional
    @Override
    public List<OrderDTO> findAll() {
        var allEntities = orderRepository.findAll();
        return orderMapper.mapToOrderDTOList(allEntities);
    }

    @Transactional
    @Override
    public OrderDTO findById(UUID id) {
        var orderById = orderRepository.findById(id).orElseThrow();
        return orderMapper.mapToOrderDTO(orderById);
    }

    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    @Override
    public OrderDTO update(OrderDTO order, UUID id) {
        orderRepository.findById(id).orElseThrow();
        var toEntity = orderMapper.mapToOrderEntity(order);
        toEntity.setId(id);
        var savingEntity = orderRepository.save(toEntity);
        return orderMapper.mapToOrderDTO(savingEntity);
    }

    @Transactional
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        BigDecimal totalPriceOfOrder = BigDecimal.ZERO;

        if (orderDTO.getLatexBalloonQuantity() != null) {
            var orderSumOfLatex =
                    latexBalloonService.removeFromBalanceAndCountPrice(orderDTO.getLatexBalloonQuantity());
            totalPriceOfOrder = totalPriceOfOrder.add(orderSumOfLatex);
        }

        if (orderDTO.getFoilBalloonQuantity() != null) {
            var orderSumOfFoil =
                    foilBalloonService.removeFromBalanceAndCountPrice(orderDTO.getFoilBalloonQuantity());
            totalPriceOfOrder = totalPriceOfOrder.add(orderSumOfFoil);
        }

        orderDTO.setTotalPrice(totalPriceOfOrder);
        orderDTO.setTotalPrice(orderPriceWithPersonalDiscount(orderDTO));

        var orderEntity = orderMapper.mapToOrderEntity(orderDTO);

        if (orderEntity.getLatexBalloonQuantity() != null) {
            for (LatexBalloonQuantityInOrder inOrder : orderEntity.getLatexBalloonQuantity()) {
                inOrder.setOrder(orderEntity);
            }
        }

        if (orderEntity.getFoilBalloonQuantity() != null) {
            for (FoilBalloonQuantityInOrder inOrder : orderEntity.getFoilBalloonQuantity()) {
                inOrder.setOrder(orderEntity);
            }
        }

        var savedEntity = orderRepository.save(orderEntity);

        return orderMapper.mapToOrderDTO(savedEntity);
    }

    @Transactional
    @Override
    public OrderDTO updateStatus(OrderStatus status, UUID id) {
        var orderFromDB = orderRepository.findById(id).orElseThrow();
        var customerFromOrder = orderFromDB.getCustomer();

        if (status.equals(OrderStatus.DONE)) {
            var resultBalance = customerFromOrder.getTotalBalance().add(orderFromDB.getTotalPrice());
            customerFromOrder.setTotalBalance(resultBalance);
            customerService.save(customerMapper.mapToCustomerDTO(customerFromOrder));
        }

        return orderMapper.mapToOrderDTO(orderFromDB);
    }

    private BigDecimal orderPriceWithPersonalDiscount(OrderDTO orderDTO) {

        var customerDTO = orderDTO.getCustomer();
        var customerFromDB = customerService.findById(customerDTO.getId());
        var customerTotalBalance = customerFromDB.getTotalBalance();

        BigDecimal orderPriceWithDiscount;

        if (customerTotalBalance.compareTo(new BigDecimal(100000)) > 0) {
            orderPriceWithDiscount = orderDTO.getTotalPrice()
                    .subtract(orderDTO.getTotalPrice().multiply(new BigDecimal(0.05)));
        } else if (customerTotalBalance.compareTo(new BigDecimal(70000)) > 0) {
            orderPriceWithDiscount = orderDTO.getTotalPrice()
                    .subtract(orderDTO.getTotalPrice().multiply(new BigDecimal(0.03)));
        } else if (customerTotalBalance.compareTo(new BigDecimal(50000)) > 0) {
            orderPriceWithDiscount = orderDTO.getTotalPrice()
                    .subtract(orderDTO.getTotalPrice().multiply(new BigDecimal(0.01)));
        } else {
            orderPriceWithDiscount = orderDTO.getTotalPrice();
        }

        return orderPriceWithDiscount.setScale(2, RoundingMode.HALF_UP);
    }
}
