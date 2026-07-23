package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.stocktransaction.StockTransactionResponse;
import com.ashutosh.inventory.entity.StockTransaction;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.StockTransactionMapper;
import com.ashutosh.inventory.repository.StockTransactionRepository;
import com.ashutosh.inventory.service.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockTransactionServiceImpl implements StockTransactionService {

    private final StockTransactionRepository stockTransactionRepository;
    private final StockTransactionMapper stockTransactionMapper;

    @Override
    public List<StockTransactionResponse> getAllStockTransactions() {

        return stockTransactionRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(stockTransactionMapper::toResponse)
                .toList();
    }

    @Override
    public StockTransactionResponse getStockTransactionById(Long transactionId) {

        StockTransaction stockTransaction = findStockTransactionById(transactionId);

        return stockTransactionMapper.toResponse(stockTransaction);
    }

    @Override
    public List<StockTransactionResponse> getStockTransactionsByProduct(Long productId) {

        return stockTransactionRepository
            .findByProductProductIdOrderByCreatedAtDesc(productId)
            .stream()
            .map(stockTransactionMapper::toResponse)
            .toList();
    }

    private StockTransaction findStockTransactionById(Long transactionId) {

        return stockTransactionRepository.findById(transactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.STOCK_TRANSACTION_NOT_FOUND));
    }
}