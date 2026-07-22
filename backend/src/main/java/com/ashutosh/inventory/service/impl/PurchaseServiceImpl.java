package com.ashutosh.inventory.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.purchase.PurchaseItemRequest;
import com.ashutosh.inventory.dto.purchase.PurchaseRequest;
import com.ashutosh.inventory.dto.purchase.PurchaseResponse;
import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.entity.Purchase;
import com.ashutosh.inventory.entity.PurchaseItem;
import com.ashutosh.inventory.entity.Supplier;
import com.ashutosh.inventory.exception.DuplicateResourceException;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.PurchaseMapper;
import com.ashutosh.inventory.repository.ProductRepository;
// import com.ashutosh.inventory.repository.PurchaseItemRepository;
import com.ashutosh.inventory.repository.PurchaseRepository;
import com.ashutosh.inventory.repository.SupplierRepository;
import com.ashutosh.inventory.service.PurchaseService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    // private final PurchaseItemRepository purchaseItemRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;


    @Override
    public PurchaseResponse createPurchase(PurchaseRequest request) {

        // Validate duplicate invoice
        validateDuplicateInvoice(request.getInvoiceNumber());

        // Fetch supplier
        Supplier supplier = findSupplier(request.getSupplierId());

        // Create Purchase entity
        Purchase purchase = PurchaseMapper.toEntity(request);
        purchase.setSupplier(supplier);

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;

        List<PurchaseItem> purchaseItems = new ArrayList<>();

        for (PurchaseItemRequest itemRequest : request.getItems()) {

            Product product = findProduct(itemRequest.getProductId());

            PurchaseItem purchaseItem = PurchaseMapper.toEntity(itemRequest);

            purchaseItem.setPurchase(purchase);
            purchaseItem.setProduct(product);

            // Calculate line total
            BigDecimal lineTotal = calculateLineTotal(
                    itemRequest.getQuantity(),
                    itemRequest.getUnitPrice());

            purchaseItem.setTotalPrice(lineTotal);

            // Calculate tax
            BigDecimal itemTax = calculateTax(
                    lineTotal,
                    itemRequest.getTaxPercentage());

            subtotal = subtotal.add(lineTotal);
            taxAmount = taxAmount.add(itemTax);

            purchaseItems.add(purchaseItem);
        }

        purchase.setPurchaseItems(purchaseItems);
        purchase.setSubtotal(subtotal);
        purchase.setTaxAmount(taxAmount);

        BigDecimal discount = request.getDiscountAmount() == null
                ? BigDecimal.ZERO
                : request.getDiscountAmount();

        purchase.setDiscountAmount(discount);

        purchase.setTotalAmount(
                calculateGrandTotal(
                        subtotal,
                        taxAmount,
                        discount
                )
        );

        Purchase savedPurchase = purchaseRepository.save(purchase);

        /*
        * TODO
        * Update Inventory
        * Create Stock Transactions
        */

        return PurchaseMapper.toResponse(savedPurchase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseResponse> getAllPurchases() {

        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseResponse getPurchaseById(Long purchaseId) {

        Purchase purchase = findPurchaseById(purchaseId);

        return PurchaseMapper.toResponse(purchase);
    }

    @Override
    public void deletePurchase(Long purchaseId) {

        Purchase purchase = findPurchaseById(purchaseId);

        purchaseRepository.delete(purchase);

        /*
        * TODO
        * Reverse Inventory
        * Reverse Stock Transactions
        */
    }

    @Override
    public PurchaseResponse updatePurchase(Long purchaseId,
                                        PurchaseRequest request) {

        validateDuplicateInvoiceForUpdate(
                purchaseId,
                request.getInvoiceNumber());

        Purchase purchase = findPurchaseById(purchaseId);

        Supplier supplier = findSupplier(request.getSupplierId());

        PurchaseMapper.updateEntity(purchase, request);
        purchase.setSupplier(supplier);

        purchase.getPurchaseItems().clear();

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;

        for (PurchaseItemRequest itemRequest : request.getItems()) {

            Product product = findProduct(itemRequest.getProductId());

            PurchaseItem purchaseItem = PurchaseMapper.toEntity(itemRequest);

            purchaseItem.setPurchase(purchase);
            purchaseItem.setProduct(product);

            BigDecimal lineTotal = calculateLineTotal(
                    itemRequest.getQuantity(),
                    itemRequest.getUnitPrice());

            purchaseItem.setTotalPrice(lineTotal);

            BigDecimal itemTax = calculateTax(
                    lineTotal,
                    itemRequest.getTaxPercentage());

            subtotal = subtotal.add(lineTotal);
            taxAmount = taxAmount.add(itemTax);

            purchase.getPurchaseItems().add(purchaseItem);
        }

        purchase.setSubtotal(subtotal);
        purchase.setTaxAmount(taxAmount);

        BigDecimal discount = request.getDiscountAmount() == null
                ? BigDecimal.ZERO
                : request.getDiscountAmount();

        purchase.setDiscountAmount(discount);

        purchase.setTotalAmount(
                calculateGrandTotal(
                        subtotal,
                        taxAmount,
                        discount));

        Purchase updatedPurchase = purchaseRepository.save(purchase);

        /*
        * TODO
        * Update Inventory Difference
        * Update Stock Transactions
        */

        return PurchaseMapper.toResponse(updatedPurchase);
    }

    private Purchase findPurchaseById(Long purchaseId) {

        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.PURCHASE_NOT_FOUND));
    }

    private Supplier findSupplier(Long supplierId) {

        return supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.SUPPLIER_NOT_FOUND));
    }

    private Product findProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.PRODUCT_NOT_FOUND));
    }

    private void validateDuplicateInvoice(String invoiceNumber) {

        if (purchaseRepository.existsByInvoiceNumber(invoiceNumber)) {

            throw new DuplicateResourceException(
                    MessageConstants.PURCHASE_ALREADY_EXISTS);
        }
    }

    private void validateDuplicateInvoiceForUpdate(
        Long purchaseId,
        String invoiceNumber) {

        purchaseRepository.findByInvoiceNumber(invoiceNumber)
                .ifPresent(existingPurchase -> {

                    if (!existingPurchase.getPurchaseId().equals(purchaseId)) {

                        throw new DuplicateResourceException(
                                MessageConstants.PURCHASE_ALREADY_EXISTS);
                    }
                });
    }

    private BigDecimal calculateLineTotal(
        BigDecimal quantity,
        BigDecimal unitPrice) {

        return quantity.multiply(unitPrice);
    }

    private BigDecimal calculateTax(
        BigDecimal lineTotal,
        BigDecimal taxPercentage) {

        return lineTotal.multiply(taxPercentage)
                .divide(BigDecimal.valueOf(100));
    }

    private BigDecimal calculateGrandTotal(
        BigDecimal subtotal,
        BigDecimal tax,
        BigDecimal discount) {

        return subtotal
                .add(tax)
                .subtract(discount);
    }


}
