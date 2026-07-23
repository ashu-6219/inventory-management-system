package com.ashutosh.inventory.dto.stocktransaction;

import com.ashutosh.inventory.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransactionResponse {

    private Long transactionId;

    private Long productId;

    private String productName;

    private TransactionType transactionType;

    private BigDecimal quantity;

    private Long referenceId;

    private String remarks;

    private Long createdBy;

    private LocalDateTime createdAt;

}