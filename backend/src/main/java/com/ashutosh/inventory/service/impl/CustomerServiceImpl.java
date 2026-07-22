package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.customer.CustomerRequest;
import com.ashutosh.inventory.dto.customer.CustomerResponse;
import com.ashutosh.inventory.entity.Customer;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.exception.DuplicateResourceException;
import com.ashutosh.inventory.mapper.CustomerMapper;
import com.ashutosh.inventory.repository.CustomerRepository;
import com.ashutosh.inventory.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        validateDuplicateCustomer(request);

        Customer customer = customerMapper.toEntity(request);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {

        Customer customer = findCustomerById(customerId);

        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId,
                                        CustomerRequest request) {

        Customer customer = findCustomerById(customerId);

        validateDuplicateCustomerForUpdate(customer, request);

        customerMapper.updateEntity(customer, request);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {

        Customer customer = findCustomerById(customerId);

        customerRepository.delete(customer);
    }

    private Customer findCustomerById(Long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.CUSTOMER_NOT_FOUND + customerId));
    }

    private void validateDuplicateCustomer(CustomerRequest request) {

        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException(
                    MessageConstants.CUSTOMER_PHONE_ALREADY_EXISTS
                            + request.getPhone());
        }

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    MessageConstants.CUSTOMER_EMAIL_ALREADY_EXISTS
                            + request.getEmail());
        }

        if (customerRepository.existsByGstNumber(request.getGstNumber())) {
            throw new DuplicateResourceException(
                    MessageConstants.CUSTOMER_GST_ALREADY_EXISTS
                            + request.getGstNumber());
        }
    }

    private void validateDuplicateCustomerForUpdate(Customer customer,
                                                CustomerRequest request) {

        if (!customer.getPhone().equals(request.getPhone())
                && customerRepository.existsByPhone(request.getPhone())) {

            throw new DuplicateResourceException(
                    MessageConstants.CUSTOMER_PHONE_ALREADY_EXISTS
                            + request.getPhone());
        }

        if (!customer.getEmail().equals(request.getEmail())
                && customerRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    MessageConstants.CUSTOMER_EMAIL_ALREADY_EXISTS
                            + request.getEmail());
        }

        if (!customer.getGstNumber().equals(request.getGstNumber())
                && customerRepository.existsByGstNumber(request.getGstNumber())) {

            throw new DuplicateResourceException(
                    MessageConstants.CUSTOMER_GST_ALREADY_EXISTS
                            + request.getGstNumber());
        }
    }
}
