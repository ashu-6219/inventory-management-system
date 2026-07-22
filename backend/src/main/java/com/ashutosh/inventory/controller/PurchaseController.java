package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.purchase.PurchaseRequest;
import com.ashutosh.inventory.dto.purchase.PurchaseResponse;
import com.ashutosh.inventory.service.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.PURCHASES)
@RequiredArgsConstructor
@Tag(name = "Purchase Management", description = "APIs for managing purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    @Operation(summary = "Create a new purchase")
    public ResponseEntity<ApiResponse<PurchaseResponse>> createPurchase(
            @Valid @RequestBody PurchaseRequest request) {

        PurchaseResponse response = purchaseService.createPurchase(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<PurchaseResponse>builder()
                                .success(true)
                                .message(MessageConstants.PURCHASE_CREATED)
                                .data(response)
                                .build()
                );
    }

    @GetMapping
    @Operation(summary = "Get All Purchases")
    public ResponseEntity<ApiResponse<List<PurchaseResponse>>> getAllPurchases() {

        List<PurchaseResponse> purchases = purchaseService.getAllPurchases();

        ApiResponse<List<PurchaseResponse>> response =
                ApiResponse.<List<PurchaseResponse>>builder()
                        .success(true)
                        .message(MessageConstants.PURCHASES_FETCHED)
                        .data(purchases)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{purchaseId}")
    @Operation(summary = "Get Purchase By ID")
    public ResponseEntity<ApiResponse<PurchaseResponse>> getPurchaseById(
            @PathVariable Long purchaseId) {

        PurchaseResponse response = purchaseService.getPurchaseById(purchaseId);

        return ResponseEntity.ok(
                ApiResponse.<PurchaseResponse>builder()
                        .success(true)
                        .message(MessageConstants.PURCHASE_FETCHED)
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{purchaseId}")
    @Operation(summary = "Update Purchase")
    public ResponseEntity<ApiResponse<PurchaseResponse>> updatePurchase(
            @PathVariable Long purchaseId,
            @Valid @RequestBody PurchaseRequest request) {

        PurchaseResponse response =
                purchaseService.updatePurchase(purchaseId, request);

        return ResponseEntity.ok(
                ApiResponse.<PurchaseResponse>builder()
                        .success(true)
                        .message(MessageConstants.PURCHASE_UPDATED)
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{purchaseId}")
    @Operation(summary = "Delete Purchase")
    public ResponseEntity<Void> deletePurchase(
            @PathVariable Long purchaseId) {

        purchaseService.deletePurchase(purchaseId);

        return ResponseEntity.noContent().build();
    }
}
