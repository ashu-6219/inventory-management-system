package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.customer.CustomerRequest;
import com.ashutosh.inventory.dto.customer.CustomerResponse;
import com.ashutosh.inventory.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {

        return Customer.builder()
                .customerName(request.getCustomerName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .gstNumber(request.getGstNumber())
                .isActive(true)
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .gstNumber(customer.getGstNumber())
                .isActive(customer.getIsActive())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public void updateEntity(Customer customer,
                             CustomerRequest request) {

        customer.setCustomerName(request.getCustomerName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setGstNumber(request.getGstNumber());
    }
}
