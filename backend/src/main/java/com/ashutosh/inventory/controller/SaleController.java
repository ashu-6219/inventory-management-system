package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.sale.SaleRequest;
import com.ashutosh.inventory.dto.sale.SaleResponse;
import com.ashutosh.inventory.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.SALE)
@RequiredArgsConstructor
@Tag(name = "Sale Management", description = "APIs for managing sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @Operation(summary = "Create a new sale")
    public ResponseEntity<ApiResponse<SaleResponse>> createSale(
            @Valid @RequestBody SaleRequest request) {

        SaleResponse response = saleService.createSale(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<SaleResponse>builder()
                                .success(true)
                                .message(MessageConstants.SALE_CREATED_SUCCESSFULLY)
                                .data(response)
                                .build()
                );
    }

    @GetMapping
    @Operation(summary = "Get All Sales")
    public ResponseEntity<ApiResponse<List<SaleResponse>>> getAllSales() {

        List<SaleResponse> response = saleService.getAllSales();

        return ResponseEntity.ok(
                ApiResponse.<List<SaleResponse>>builder()
                        .success(true)
                        .message(MessageConstants.SALES_FETCHED_SUCCESSFULLY)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{saleId}")
    @Operation(summary = "Get Sale by ID")
    public ResponseEntity<ApiResponse<SaleResponse>> getSaleById(
            @PathVariable Long saleId) {

        SaleResponse response = saleService.getSaleById(saleId);

        return ResponseEntity.ok(
                ApiResponse.<SaleResponse>builder()
                        .success(true)
                        .message(MessageConstants.SALE_FETCHED_SUCCESSFULLY)
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{saleId}")
    @Operation(summary = "Update a sale")
    public ResponseEntity<ApiResponse<SaleResponse>> updateSale(
            @PathVariable Long saleId,
            @Valid @RequestBody SaleRequest request) {

        SaleResponse response =
                saleService.updateSale(saleId, request);

        return ResponseEntity.ok(
                ApiResponse.<SaleResponse>builder()
                        .success(true)
                        .message(MessageConstants.SALE_UPDATED_SUCCESSFULLY)
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{saleId}")
    @Operation(summary = "Delete a sale")
    public ResponseEntity<ApiResponse<Void>> deleteSale(
            @PathVariable Long saleId) {

        saleService.deleteSale(saleId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(MessageConstants.SALE_DELETED_SUCCESSFULLY)
                        .build()
        );
    }

}