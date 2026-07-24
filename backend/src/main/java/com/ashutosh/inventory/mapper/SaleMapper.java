package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.sale.*;
import com.ashutosh.inventory.entity.Sale;
import com.ashutosh.inventory.entity.SaleItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SaleMapper {

    // =========================
    // Request -> Entity
    // =========================

    public Sale toEntity(SaleRequest request) {

        return Sale.builder()
                .invoiceNumber(request.getInvoiceNumber())
                .saleDate(request.getSaleDate())
                .discountAmount(request.getDiscountAmount())
                .remarks(request.getRemarks())
                .build();
    }

    public SaleItem toEntity(SaleItemRequest request) {

        return SaleItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .taxPercentage(request.getTaxPercentage())
                .build();
    }

    // =========================
    // Entity -> Response
    // =========================

    public SaleResponse toResponse(Sale sale) {

        return SaleResponse.builder()
                .saleId(sale.getSaleId())
                .customerId(sale.getCustomer().getCustomerId())
                .customerName(sale.getCustomer().getCustomerName())
                .invoiceNumber(sale.getInvoiceNumber())
                .saleDate(sale.getSaleDate())
                .subtotal(sale.getSubtotal())
                .taxAmount(sale.getTaxAmount())
                .discountAmount(sale.getDiscountAmount())
                .totalAmount(sale.getTotalAmount())
                .remarks(sale.getRemarks())
                .saleItems(toSaleItemResponseList(sale.getSaleItems()))
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .build();
    }

    public SaleItemResponse toResponse(SaleItem saleItem) {

        return SaleItemResponse.builder()
                .saleItemId(saleItem.getSaleItemId())
                .productId(saleItem.getProduct().getProductId())
                .productName(saleItem.getProduct().getProductName())
                .quantity(saleItem.getQuantity())
                .unitPrice(saleItem.getUnitPrice())
                .taxPercentage(saleItem.getTaxPercentage())
                .totalPrice(saleItem.getTotalPrice())
                .createdAt(saleItem.getCreatedAt())
                .updatedAt(saleItem.getUpdatedAt())
                .build();
    }

    // =========================
    // List Mapping
    // =========================

    public List<SaleItemResponse> toSaleItemResponseList(List<SaleItem> saleItems) {

        return saleItems.stream()
                .map(SaleMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void updateEntity(Sale sale, SaleRequest request) {

        sale.setInvoiceNumber(request.getInvoiceNumber());
        sale.setSaleDate(request.getSaleDate());
        sale.setDiscountAmount(request.getDiscountAmount());
        sale.setRemarks(request.getRemarks());
    }

}