package com.ashutosh.inventory.dto.purchase;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponse {
    private Long purchaseId;

    private Long supplierId;
    private String supplierName;

    private String invoiceNumber;

    private LocalDate purchaseDate;

    private BigDecimal subtotal;

    private BigDecimal taxAmount;

    private BigDecimal discountAmount;

    private BigDecimal totalAmount;

    private String remarks;

    private List<PurchaseItemResponse> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
