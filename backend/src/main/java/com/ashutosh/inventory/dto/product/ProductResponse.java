package com.ashutosh.inventory.dto.product;

import com.ashutosh.inventory.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long productId;

    private String productName;

    private String sku;

    private String barcode;

    private Long categoryId;

    private String categoryName;

    private Long supplierId;

    private String supplierName;

    private BigDecimal costPrice;

    private BigDecimal sellingPrice;

    private BigDecimal taxPercentage;

    private Unit unit;

    private Integer reorderLevel;

    private String description;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
