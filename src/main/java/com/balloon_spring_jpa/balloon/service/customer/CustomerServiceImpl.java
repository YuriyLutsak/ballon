package com.balloon_spring_jpa.balloon.service.customer;

import com.balloon_spring_jpa.balloon.dto.CustomerDTO;
import com.balloon_spring_jpa.balloon.dto.mapper.CustomerMapper;
import com.balloon_spring_jpa.balloon.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    /**
     * Добавить логику скидки с учетом Сustomer.totalBalabce.
     */



    @Transactional
    @Override
    public List<CustomerDTO> findAll() {
        var customerList = customerRepository.findAll();
        return customerMapper.mapToCustomerListDTO(customerList);
    }

    @Transactional
    @Override
    public List<CustomerDTO> getAllCustomersWithOrders() {
        var allWithOrders = customerRepository.findAllWithOrders();
        return customerMapper.mapToCustomerListDTO(allWithOrders);
    }

    @Transactional
    @Override
    public CustomerDTO findById(UUID id) {
        var customerById = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no such entity"));
        return customerMapper.mapToCustomerDTO(customerById);
    }

    @Transactional
    @Override
    public CustomerDTO save(CustomerDTO customer) {
        var toEntity = customerMapper.mapToCustomerEntity(customer);
        var savingEntity = customerRepository.save(toEntity); // хайбернет не сохраняет в бд(тут хайбер сохраняет в бд) значит проблема с энтити скорее всего со связями
        return customerMapper.mapToCustomerDTO(savingEntity);
    }

    @Transactional
    @Override
    public CustomerDTO update(CustomerDTO customer, UUID id) {
        customerRepository.findById(id).orElseThrow();
        var toEntity = customerMapper.mapToCustomerEntity(customer);
        toEntity.setId(id);
        var savingEntity = customerRepository.save(toEntity);
        return customerMapper.mapToCustomerDTO(savingEntity);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        var customerById = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customerById);
    }
}
