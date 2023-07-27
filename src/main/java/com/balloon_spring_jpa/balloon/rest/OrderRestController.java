package com.balloon_spring_jpa.balloon.rest;

import com.balloon_spring_jpa.balloon.balloonEnum.OrderStatus;
import com.balloon_spring_jpa.balloon.dto.OrderDTO;
import com.balloon_spring_jpa.balloon.service.order.OrderServiceImpl;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderRestController {

    private final OrderServiceImpl orderService;

    @GetMapping("/orders")
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/by/{customerId}")
    public List<OrderDTO> findAllOrdersByCustomerId(@PathVariable UUID customerId){
        return orderService.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/orders/{id}")
    public OrderDTO findById(@PathVariable UUID id) {
        return orderService.findById( id);
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable UUID id) {
         orderService.delete(id);
    }

    @PostMapping("/orders")
    public OrderDTO save(@RequestBody OrderDTO order) {
        return orderService.save(order);
    }

    @PutMapping("/orders/{id}")
    public OrderDTO update(@RequestBody OrderDTO order, @PathVariable UUID id) {
        return orderService.update(order, id);
    }

    @PatchMapping("/orders/{id}/{status}")
    public OrderDTO updateStatus(@PathVariable UUID id, @PathVariable OrderStatus status){
        return orderService.updateStatus(status, id);
    }
}
