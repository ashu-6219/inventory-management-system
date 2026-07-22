package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.purchase.PurchaseRequest;
import com.ashutosh.inventory.dto.purchase.PurchaseResponse;

import java.util.List;

public interface PurchaseService {
    PurchaseResponse createPurchase(PurchaseRequest request);

    List<PurchaseResponse> getAllPurchases();

    PurchaseResponse getPurchaseById(Long purchaseId);

    PurchaseResponse updatePurchase(Long purchaseId, PurchaseRequest request);

    void deletePurchase(Long purchaseId);
}
