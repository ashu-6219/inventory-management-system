package com.ashutosh.inventory.dto.sale;

import com.ashutosh.inventory.constants.ValidationMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {

    @NotNull(message = ValidationMessages.CUSTOMER_ID_REQUIRED)
    private Long customerId;

    @NotBlank(message = ValidationMessages.INVOICE_NUMBER_REQUIRED)
    @Size(max = 100,
            message = ValidationMessages.INVOICE_NUMBER_SIZE)
    private String invoiceNumber;

    @NotNull(message = ValidationMessages.SALE_DATE_REQUIRED)
    private LocalDate saleDate;

    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Size(max = 300,
            message = ValidationMessages.REMARKS_SIZE)
    private String remarks;

    @Valid
    @NotEmpty(message = ValidationMessages.SALE_ITEMS_REQUIRED)
    private List<SaleItemRequest> items;

}