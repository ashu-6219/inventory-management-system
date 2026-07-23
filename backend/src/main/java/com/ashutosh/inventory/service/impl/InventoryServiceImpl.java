package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.inventory.InventoryResponse;
import com.ashutosh.inventory.entity.Inventory;
// import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.InventoryMapper;
import com.ashutosh.inventory.repository.InventoryRepository;
// import com.ashutosh.inventory.repository.ProductRepository;
import com.ashutosh.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    // private final ProductRepository productRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                // ProductRepository productRepository,
                                InventoryMapper inventoryMapper) {

        this.inventoryRepository = inventoryRepository;
        // this.productRepository = productRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Override
    public InventoryResponse getInventoryById(Long inventoryId) {

        Inventory inventory = findInventoryById(inventoryId);

        return inventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponse getInventoryByProduct(Long productId) {

        // Product product = findProduct(productId);

        // Inventory inventory = inventoryRepository.findByProduct(product)
        //         .orElseThrow(() -> new ResourceNotFoundException(
        //                 MessageConstants.INVENTORY_NOT_FOUND));
        Inventory inventory = inventoryRepository
            .findByProductProductId(productId)
            .orElseThrow(() -> new ResourceNotFoundException(
                    MessageConstants.INVENTORY_NOT_FOUND));

        return inventoryMapper.toResponse(inventory);
    }

    // ===========================
    // Helper Methods
    // ===========================

    private Inventory findInventoryById(Long inventoryId) {

        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.INVENTORY_NOT_FOUND));
    }

    // private Product findProduct(Long productId) {

    //     return productRepository.findById(productId)
    //             .orElseThrow(() -> new ResourceNotFoundException(
    //                     MessageConstants.PRODUCT_NOT_FOUND));
    // }

}
