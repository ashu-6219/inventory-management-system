package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.SupplierRequest;
import com.ashutosh.inventory.dto.SupplierResponse;
import com.ashutosh.inventory.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequest request){

        return Supplier.builder()
                .supplierName(request.getSupplierName())
                .contactPerson(request.getContactPerson())
                .phone(request.getPhone())
                .email(request.getEmail())
                .gstNumber(request.getGstNumber())
                .address(request.getAddress())
                .paymentMode(request.getPaymentMode())
                .creditDays(request.getCreditDays())
                .isActive(true)
                .build();
    }

    public SupplierResponse toResponse(Supplier supplier) {

        return SupplierResponse.builder()
                .supplierId(supplier.getSupplierId())
                .supplierName(supplier.getSupplierName())
                .contactPerson(supplier.getContactPerson())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .gstNumber(supplier.getGstNumber())
                .address(supplier.getAddress())
                .paymentMode(supplier.getPaymentMode())
                .creditDays(supplier.getCreditDays())
                .isActive(supplier.getIsActive())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }

    public void updateEntity(Supplier supplier, SupplierRequest request) {

        supplier.setSupplierName(request.getSupplierName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setGstNumber(request.getGstNumber());
        supplier.setAddress(request.getAddress());
        supplier.setPaymentMode(request.getPaymentMode());
        supplier.setCreditDays(request.getCreditDays());

    }
}
