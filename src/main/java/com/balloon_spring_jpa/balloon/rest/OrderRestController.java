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
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderServiceImpl orderService;

    @GetMapping("")
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/by/{customerId}")
    public List<OrderDTO> findAllOrdersByCustomerId(@PathVariable UUID customerId){
        return orderService.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable UUID id) {
        return orderService.findById( id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
         orderService.delete(id);
    }

    @PostMapping("")
    public OrderDTO save(@RequestBody OrderDTO order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public OrderDTO update(@RequestBody OrderDTO order, @PathVariable UUID id) {
        return orderService.update(order, id);
    }

    @PatchMapping("/{id}/{status}")
    public OrderDTO updateStatus(@PathVariable UUID id, @PathVariable OrderStatus status){
        return orderService.updateStatus(status, id);
    }
}
