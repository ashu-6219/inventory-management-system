package com.ashutosh.inventory.dto.customer;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomerResponse {
    private Long customerId;

    private String customerName;

    private String phone;

    private String email;

    private String address;

    private String gstNumber;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
