package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.inventory.InventoryResponse;
import com.ashutosh.inventory.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.INVENTORY)
@RequiredArgsConstructor
@Tag(name = "Inventory Management", description = "APIs for managing inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @Operation(summary = "Get all inventory")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getAllInventory() {

        List<InventoryResponse> response = inventoryService.getAllInventory();

        return ResponseEntity.ok(
                ApiResponse.<List<InventoryResponse>>builder()
                        .success(true)
                        .message(MessageConstants.INVENTORY_FETCHED_SUCCESSFULLY)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{inventoryId}")
    @Operation(summary = "Get inventory by ID")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryById(
            @PathVariable Long inventoryId) {

        InventoryResponse response = inventoryService.getInventoryById(inventoryId);

        return ResponseEntity.ok(
                ApiResponse.<InventoryResponse>builder()
                        .success(true)
                        .message(MessageConstants.INVENTORY_FETCHED_SUCCESSFULLY)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get inventory by Product ID")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryByProduct(
            @PathVariable Long productId) {

        InventoryResponse response = inventoryService.getInventoryByProduct(productId);

        return ResponseEntity.ok(
                ApiResponse.<InventoryResponse>builder()
                        .success(true)
                        .message(MessageConstants.INVENTORY_FETCHED_SUCCESSFULLY)
                        .data(response)
                        .build()
        );
    }
}