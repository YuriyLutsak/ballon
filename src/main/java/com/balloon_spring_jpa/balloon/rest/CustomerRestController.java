package com.balloon_spring_jpa.balloon.rest;

import com.balloon_spring_jpa.balloon.dto.CustomerDTO;
import com.balloon_spring_jpa.balloon.service.customer.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerServiceImpl service;

    @GetMapping
    public List<CustomerDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/orders")
    public List<CustomerDTO> findAllWithOrders(){
       return service.getAllCustomersWithOrders();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public CustomerDTO save(@RequestBody CustomerDTO customer) {
        return service.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@RequestBody CustomerDTO customer, @PathVariable UUID id) {
        return service.update(customer, id);
    }
}
