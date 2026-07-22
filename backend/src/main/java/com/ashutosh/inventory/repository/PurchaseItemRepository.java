package com.ashutosh.inventory.repository;

import com.ashutosh.inventory.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

}
