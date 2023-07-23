package com.balloon_spring_jpa.balloon.rest;

import com.balloon_spring_jpa.balloon.balloonEnum.OrderStatus;
import com.balloon_spring_jpa.balloon.dto.OrderDTO;
import com.balloon_spring_jpa.balloon.service.order.OrderServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderRestController {

    private final OrderServiceImpl orderService;

    @GetMapping("/orders")
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public OrderDTO findById(@PathVariable UUID id) {
        return orderService.findById( id);
    }

    @DeleteMapping("/orders{id}")
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
