package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.inventory.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventoryById(Long inventoryId);

    InventoryResponse getInventoryByProduct(Long productId);
}
