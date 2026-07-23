package com.ashutosh.inventory.repository;

import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

    List<StockTransaction> findByProduct(Product product);

    // List<StockTransaction> findByProductProductId(Long productId);
    List<StockTransaction> findAllByOrderByCreatedAtDesc();

    List<StockTransaction> findByProductProductIdOrderByCreatedAtDesc(Long productId);

}