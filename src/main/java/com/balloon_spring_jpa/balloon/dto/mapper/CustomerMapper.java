package com.balloon_spring_jpa.balloon.dto.mapper;

import com.balloon_spring_jpa.balloon.dto.CustomerDTO;
import com.balloon_spring_jpa.balloon.entity.Customer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("CustomerMapper")
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer mapToCustomerEntity(CustomerDTO customerDTO);

    @Named("mapToCustomerDTO")
    @Mapping(target = "orders", ignore = true)
    CustomerDTO mapToCustomerDTO(Customer customer);

    @IterableMapping(qualifiedByName = "mapToCustomerDTO")
    List<CustomerDTO> mapToCustomerListDTO(List<Customer> customerList);
}
