package com.ashutosh.inventory.dto;

import com.ashutosh.inventory.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {

    private Long supplierId;

    private String supplierName;

    private String contactPerson;

    private String phone;

    private String email;

    private String gstNumber;

    private String address;

    private PaymentMode paymentMode;

    private Integer creditDays;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
