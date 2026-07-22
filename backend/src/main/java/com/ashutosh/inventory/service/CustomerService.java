package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.customer.CustomerRequest;
import com.ashutosh.inventory.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long customerId);

    CustomerResponse updateCustomer(Long customerId,
                                    CustomerRequest request);

    void deleteCustomer(Long customerId);
}
