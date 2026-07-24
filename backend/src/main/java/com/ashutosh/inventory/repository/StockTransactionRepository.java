package com.ashutosh.inventory.repository;

import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.entity.StockTransaction;
import com.ashutosh.inventory.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

    Optional<StockTransaction> findById(Long transactionId);

    List<StockTransaction> findByProduct(Product product);

    // List<StockTransaction> findByProductProductId(Long productId);
    List<StockTransaction> findAllByOrderByCreatedAtDesc();

    List<StockTransaction> findByProductProductIdOrderByCreatedAtDesc(Long productId);

    List<StockTransaction> findByTransactionTypeAndReferenceId(
            TransactionType transactionType,
            Long referenceId
    );

    void deleteByTransactionTypeAndReferenceId(
            TransactionType transactionType,
            Long referenceId
    );

}