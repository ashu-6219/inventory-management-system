package com.ashutosh.inventory.dto.sale;

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
public class SaleResponse {

    private Long saleId;

    private Long customerId;

    private String customerName;

    private String invoiceNumber;

    private LocalDate saleDate;

    private BigDecimal subtotal;

    private BigDecimal taxAmount;

    private BigDecimal discountAmount;

    private BigDecimal totalAmount;

    private String remarks;

    private List<SaleItemResponse> saleItems;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}