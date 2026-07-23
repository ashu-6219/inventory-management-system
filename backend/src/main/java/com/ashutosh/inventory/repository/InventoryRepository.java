package com.ashutosh.inventory.repository;

import com.ashutosh.inventory.entity.Inventory;
import com.ashutosh.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct(Product product);

    Optional<Inventory> findByProductProductId(Long productId);
}
