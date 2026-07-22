package com.ashutosh.inventory.dto.purchase;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemResponse {
    private Long purchaseItemId;

    private Long productId;
    private String productName;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal taxPercentage;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
