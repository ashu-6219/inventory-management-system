package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.purchase.*;
import com.ashutosh.inventory.entity.Purchase;
import com.ashutosh.inventory.entity.PurchaseItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PurchaseMapper {

    // =========================
    // Request -> Entity
    // =========================

    public Purchase toEntity(PurchaseRequest request) {

        return Purchase.builder()
                .invoiceNumber(request.getInvoiceNumber())
                .purchaseDate(request.getPurchaseDate())
                .discountAmount(request.getDiscountAmount())
                .remarks(request.getRemarks())
                .build();
    }

    public PurchaseItem toEntity(PurchaseItemRequest request) {

        return PurchaseItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .taxPercentage(request.getTaxPercentage())
                .build();
    }

    // =========================
    // Entity -> Response
    // =========================

    public PurchaseResponse toResponse(Purchase purchase) {

        return PurchaseResponse.builder()
                .purchaseId(purchase.getPurchaseId())
                .supplierId(purchase.getSupplier().getSupplierId())
                .supplierName(purchase.getSupplier().getSupplierName())
                .invoiceNumber(purchase.getInvoiceNumber())
                .purchaseDate(purchase.getPurchaseDate())
                .subtotal(purchase.getSubtotal())
                .taxAmount(purchase.getTaxAmount())
                .discountAmount(purchase.getDiscountAmount())
                .totalAmount(purchase.getTotalAmount())
                .remarks(purchase.getRemarks())
                .items(toPurchaseItemResponseList(purchase.getPurchaseItems()))
                .createdAt(purchase.getCreatedAt())
                .updatedAt(purchase.getUpdatedAt())
                .build();
    }

    public PurchaseItemResponse toResponse(PurchaseItem purchaseItem) {

        return PurchaseItemResponse.builder()
                .purchaseItemId(purchaseItem.getPurchaseItemId())
                .productId(purchaseItem.getProduct().getProductId())
                .productName(purchaseItem.getProduct().getProductName())
                .quantity(purchaseItem.getQuantity())
                .unitPrice(purchaseItem.getUnitPrice())
                .taxPercentage(purchaseItem.getTaxPercentage())
                .totalPrice(purchaseItem.getTotalPrice())
                .createdAt(purchaseItem.getCreatedAt())
                .updatedAt(purchaseItem.getUpdatedAt())
                .build();
    }

    // =========================
    // List Mapping
    // =========================

    public List<PurchaseItemResponse> toPurchaseItemResponseList(List<PurchaseItem> purchaseItems) {

        return purchaseItems.stream()
                .map(PurchaseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void updateEntity(Purchase purchase, PurchaseRequest request) {
        purchase.setInvoiceNumber(request.getInvoiceNumber());
        purchase.setPurchaseDate(request.getPurchaseDate());
        purchase.setDiscountAmount(request.getDiscountAmount());
        purchase.setRemarks(request.getRemarks());
    }

}
