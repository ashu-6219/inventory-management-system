package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.stocktransaction.StockTransactionResponse;

import java.util.List;

public interface StockTransactionService {

    List<StockTransactionResponse> getAllStockTransactions();

    StockTransactionResponse getStockTransactionById(Long transactionId);

    List<StockTransactionResponse> getStockTransactionsByProduct(Long productId);

}