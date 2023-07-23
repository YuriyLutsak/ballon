package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.CustomerDTO;
import com.balloon_spring_jpa.balloon.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("CustomerMapper")
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer mapToCustomerEntity(CustomerDTO customer);

    @Named("mapToCustomerDTO")
    @Mapping(target = "orders", ignore = true)
    CustomerDTO mapToCustomerDTO(Customer customerEntity);

    List<CustomerDTO> mapToCustomerListDTO(List<Customer> customerList);
}
