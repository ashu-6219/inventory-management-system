package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.stocktransaction.StockTransactionResponse;
import com.ashutosh.inventory.entity.StockTransaction;
import org.springframework.stereotype.Component;

@Component
public class StockTransactionMapper {

    public StockTransactionResponse toResponse(StockTransaction stockTransaction) {

        return StockTransactionResponse.builder()
                .transactionId(stockTransaction.getTransactionId())
                .productId(stockTransaction.getProduct().getProductId())
                .productName(stockTransaction.getProduct().getProductName())
                .transactionType(stockTransaction.getTransactionType())
                .quantity(stockTransaction.getQuantity())
                .referenceId(stockTransaction.getReferenceId())
                .remarks(stockTransaction.getRemarks())
                .createdBy(stockTransaction.getCreatedBy())
                .createdAt(stockTransaction.getCreatedAt())
                .build();
    }

}
