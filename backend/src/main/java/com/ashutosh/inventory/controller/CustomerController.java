package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.customer.CustomerRequest;
import com.ashutosh.inventory.dto.customer.CustomerResponse;
import com.ashutosh.inventory.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CUSTOMERS)
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse response = customerService.createCustomer(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CustomerResponse>builder()
                        .success(true)
                        .message(MessageConstants.CUSTOMER_CREATED)
                        .data(response)
                        .build());
    }

    @GetMapping
    @Operation(summary = "Get All Customers")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {

        List<CustomerResponse> customers =
                customerService.getAllCustomers();

        ApiResponse<List<CustomerResponse>> response =
                ApiResponse.<List<CustomerResponse>>builder()
                        .success(true)
                        .message(MessageConstants.CUSTOMERS_FETCHED)
                        .data(customers)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get Customer By ID")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(
            @PathVariable Long customerId) {

        CustomerResponse response =
                customerService.getCustomerById(customerId);

        return ResponseEntity.ok(
                ApiResponse.<CustomerResponse>builder()
                        .success(true)
                        .message(MessageConstants.CUSTOMER_FETCHED)
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{customerId}")
    @Operation(summary = "Update Customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse response =
                customerService.updateCustomer(customerId, request);

        return ResponseEntity.ok(
                ApiResponse.<CustomerResponse>builder()
                        .success(true)
                        .message(MessageConstants.CUSTOMER_UPDATED)
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{customerId}")
    @Operation(summary = "Delete customer")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }
}
