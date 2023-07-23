package com.balloon_spring_jpa.balloon.service.customer;

import com.balloon_spring_jpa.balloon.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(UUID id);

    void delete(UUID id);

    CustomerDTO save(CustomerDTO customer);

    CustomerDTO update(CustomerDTO customer, UUID id);

    List<CustomerDTO> getAllCustomersWithOrders();
}
