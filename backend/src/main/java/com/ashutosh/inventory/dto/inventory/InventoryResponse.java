package com.ashutosh.inventory.dto.inventory;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
    private Long inventoryId;

    private Long productId;

    private String productName;

    private BigDecimal quantity;

    private BigDecimal reservedQuantity;

    private BigDecimal damagedQuantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
