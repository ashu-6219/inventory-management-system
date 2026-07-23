package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.stocktransaction.StockTransactionResponse;
import com.ashutosh.inventory.service.StockTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.STOCK_TRANSACTION)
@RequiredArgsConstructor
@Tag(name = "Stock Transaction", description = "Stock Transaction Management APIs")
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    @GetMapping
    @Operation(summary = "Get all stock transactions")
    public ResponseEntity<ApiResponse<List<StockTransactionResponse>>> getAllStockTransactions() {

        List<StockTransactionResponse> stockTransactions =
                stockTransactionService.getAllStockTransactions();

        return ResponseEntity.ok(
                ApiResponse.<List<StockTransactionResponse>>builder()
                        .success(true)
                        .message(MessageConstants.STOCK_TRANSACTIONS_FETCHED_SUCCESSFULLY)
                        .data(stockTransactions)
                        .build()
        );
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "Get stock transaction by ID")
    public ResponseEntity<ApiResponse<StockTransactionResponse>> getStockTransactionById(
            @PathVariable Long transactionId) {

        StockTransactionResponse stockTransaction =
                stockTransactionService.getStockTransactionById(transactionId);

        return ResponseEntity.ok(
                ApiResponse.<StockTransactionResponse>builder()
                        .success(true)
                        .message(MessageConstants.STOCK_TRANSACTION_FETCHED_SUCCESSFULLY)
                        .data(stockTransaction)
                        .build()
        );
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get stock transactions by product")
    public ResponseEntity<ApiResponse<List<StockTransactionResponse>>> getStockTransactionsByProduct(
            @PathVariable Long productId) {

        List<StockTransactionResponse> stockTransactions =
                stockTransactionService.getStockTransactionsByProduct(productId);

        return ResponseEntity.ok(
                ApiResponse.<List<StockTransactionResponse>>builder()
                        .success(true)
                        .message(MessageConstants.STOCK_TRANSACTIONS_FETCHED_SUCCESSFULLY)
                        .data(stockTransactions)
                        .build()
        );
    }

}