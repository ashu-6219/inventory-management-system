package com.ashutosh.inventory.dto.sale;

import com.ashutosh.inventory.constants.ValidationMessages;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemRequest {

    @NotNull(message = ValidationMessages.PRODUCT_ID_REQUIRED)
    private Long productId;

    @NotNull(message = ValidationMessages.QUANTITY_REQUIRED)
    @DecimalMin(value = "0.01", inclusive = true,
            message = ValidationMessages.INVALID_QUANTITY)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal quantity;

    @NotNull(message = ValidationMessages.UNIT_PRICE_REQUIRED)
    @DecimalMin(value = "0.01", inclusive = true,
            message = ValidationMessages.INVALID_UNIT_PRICE)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal unitPrice;

    @NotNull(message = ValidationMessages.TAX_PERCENTAGE_REQUIRED)
    @DecimalMin(value = "0.00", inclusive = true,
            message = ValidationMessages.INVALID_TAX_PERCENTAGE)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal taxPercentage;
}