package com.ashutosh.inventory.dto.product;

import com.ashutosh.inventory.constants.ValidationMessages;
import com.ashutosh.inventory.enums.Unit;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = ValidationMessages.PRODUCT_NAME_REQUIRED)
    @Size(min = 3, max = 150,
            message = ValidationMessages.PRODUCT_NAME_SIZE)
    private String productName;

    @NotBlank(message = ValidationMessages.SKU_REQUIRED)
    @Size(max = 50,
            message = ValidationMessages.SKU_SIZE)
    private String sku;

    @Size(max = 50,
            message = ValidationMessages.BARCODE_SIZE)
    private String barcode;

    @NotNull(message = ValidationMessages.CATEGORY_ID_REQUIRED)
    private Long categoryId;

    @NotNull(message = ValidationMessages.SUPPLIER_ID_REQUIRED)
    private Long supplierId;

    @NotNull(message = ValidationMessages.COST_PRICE_REQUIRED)
    @DecimalMin(value = "0.00",
            message = "Cost price cannot be negative.")
    private BigDecimal costPrice;

    @NotNull(message = ValidationMessages.SELLING_PRICE_REQUIRED)
    @DecimalMin(value = "0.00",
            message = "Selling price cannot be negative.")
    private BigDecimal sellingPrice;

    @DecimalMin(value = "0.00",
            message = ValidationMessages.TAX_PERCENTAGE_INVALID)
    private BigDecimal taxPercentage;

    @NotNull(message = ValidationMessages.UNIT_REQUIRED)
    private Unit unit;

    @Min(value = 0,
            message = ValidationMessages.REORDER_LEVEL_INVALID)
    private Integer reorderLevel;

    @Size(max = 500,
            message = ValidationMessages.DESCRIPTION_SIZE)
    private String description;

}