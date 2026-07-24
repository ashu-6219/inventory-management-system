package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.sale.SaleItemRequest;
import com.ashutosh.inventory.dto.sale.SaleRequest;
import com.ashutosh.inventory.dto.sale.SaleResponse;
import com.ashutosh.inventory.entity.Customer;
import com.ashutosh.inventory.entity.Inventory;
import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.entity.Sale;
import com.ashutosh.inventory.entity.SaleItem;
import com.ashutosh.inventory.entity.StockTransaction;
import com.ashutosh.inventory.enums.TransactionType;
import com.ashutosh.inventory.exception.DuplicateResourceException;
import com.ashutosh.inventory.exception.InsufficientStockException;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.SaleMapper;
import com.ashutosh.inventory.repository.CustomerRepository;
import com.ashutosh.inventory.repository.InventoryRepository;
import com.ashutosh.inventory.repository.ProductRepository;
import com.ashutosh.inventory.repository.SaleRepository;
import com.ashutosh.inventory.repository.StockTransactionRepository;
import com.ashutosh.inventory.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleServiceImpl implements SaleService {
    
    private final SaleRepository saleRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final InventoryRepository inventoryRepository;

    private final StockTransactionRepository stockTransactionRepository;


    @Override
    public SaleResponse createSale(SaleRequest request) {

        // Validate duplicate invoice
        validateDuplicateInvoice(request.getInvoiceNumber());

        // Fetch customer
        Customer customer = findCustomer(request.getCustomerId());

        // Create Sale entity
        Sale sale = SaleMapper.toEntity(request);
        sale.setCustomer(customer);

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;

        List<SaleItem> saleItems = new ArrayList<>();

        Map<Long, BigDecimal> requestedQuantities = new HashMap<>();

        for (SaleItemRequest itemRequest : request.getItems()) {

            requestedQuantities.merge(
                    itemRequest.getProductId(),
                    itemRequest.getQuantity(),
                    BigDecimal::add);
        }

        for (Map.Entry<Long, BigDecimal> entry : requestedQuantities.entrySet()) {

            Product product = findProduct(entry.getKey());

            Inventory inventory =
                    findInventoryByProductId(product.getProductId());

            validateStockAvailability(
                    product,
                    inventory,
                    entry.getValue());
        }

        for (SaleItemRequest itemRequest : request.getItems()) {

            Product product = findProduct(itemRequest.getProductId());

            // Inventory inventory =
            //         findInventoryByProductId(product.getProductId());

            // validateStockAvailability(
            //         product,
            //         inventory,
            //         itemRequest.getQuantity());

            SaleItem saleItem = SaleMapper.toEntity(itemRequest);

            saleItem.setSale(sale);
            saleItem.setProduct(product);

            // Calculate line total
            BigDecimal lineTotal =
                    calculateLineTotal(
                            itemRequest.getQuantity(),
                            itemRequest.getUnitPrice());

            saleItem.setTotalPrice(lineTotal);

            // Calculate tax
            BigDecimal itemTax =
                    calculateTax(
                            lineTotal,
                            itemRequest.getTaxPercentage());

            subtotal = subtotal.add(lineTotal);
            taxAmount = taxAmount.add(itemTax);

            saleItems.add(saleItem);
        }

        sale.setSaleItems(saleItems);
        sale.setSubtotal(subtotal);
        sale.setTaxAmount(taxAmount);

        BigDecimal discount =
                request.getDiscountAmount() == null
                        ? BigDecimal.ZERO
                        : request.getDiscountAmount();

        sale.setDiscountAmount(discount);

        sale.setTotalAmount(
                calculateGrandTotal(
                        subtotal,
                        taxAmount,
                        discount));

        Sale savedSale = saleRepository.save(sale);

        /*
         * Update Inventory
         * Create Stock Transactions
         */

        for (SaleItem item : savedSale.getSaleItems()) {

            updateInventory(
                    item.getProduct(),
                    item.getQuantity());

            createStockTransaction(
                    item.getProduct(),
                    item.getQuantity(),
                    savedSale.getSaleId());
        }

        return SaleMapper.toResponse(savedSale);
    }


    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> getAllSales() {

        return saleRepository.findAll()
                .stream()
                .map(SaleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SaleResponse getSaleById(Long saleId) {

        Sale sale = findSaleById(saleId);

        return SaleMapper.toResponse(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Long saleId) {

        Sale sale = findSaleById(saleId);

         /*
        * Restore Inventory
        */
        restoreInventory(sale);

        /*
        * Delete Stock Transactions
        */
        deleteStockTransactions(sale.getSaleId());

        saleRepository.delete(sale);

    }

    @Override
    @Transactional
    public SaleResponse updateSale(Long saleId,
                                SaleRequest request) {

        validateDuplicateInvoiceForUpdate(
                saleId,
                request.getInvoiceNumber());

        Sale sale = findSaleById(saleId);
        
        /*
        * Restore Inventory
        */
        restoreInventory(sale);

        /*
        * Delete Stock Transactions
        */
        deleteStockTransactions(sale.getSaleId());

        Customer customer = findCustomer(request.getCustomerId());

        SaleMapper.updateEntity(sale, request);
        sale.setCustomer(customer);

        sale.getSaleItems().clear();

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;

        Map<Long, BigDecimal> requestedQuantities = new HashMap<>();

        for (SaleItemRequest itemRequest : request.getItems()) {

            requestedQuantities.merge(
                    itemRequest.getProductId(),
                    itemRequest.getQuantity(),
                    BigDecimal::add);
        }

        for (Map.Entry<Long, BigDecimal> entry : requestedQuantities.entrySet()) {

            Product product = findProduct(entry.getKey());

            Inventory inventory =
                    findInventoryByProductId(product.getProductId());

            validateStockAvailability(
                    product,
                    inventory,
                    entry.getValue());
        }

        // List<SaleItem> saleItems = new ArrayList<>();

        for (SaleItemRequest itemRequest : request.getItems()) {

            Product product = findProduct(itemRequest.getProductId());

            // Inventory inventory =
            //         findInventoryByProductId(product.getProductId());

            // validateStockAvailability(
            //         product,
            //         inventory,
            //         itemRequest.getQuantity());

            SaleItem saleItem = SaleMapper.toEntity(itemRequest);

            saleItem.setSale(sale);
            saleItem.setProduct(product);

            BigDecimal lineTotal = calculateLineTotal(
                    itemRequest.getQuantity(),
                    itemRequest.getUnitPrice());

            saleItem.setTotalPrice(lineTotal);

            BigDecimal itemTax = calculateTax(
                    lineTotal,
                    itemRequest.getTaxPercentage());

            subtotal = subtotal.add(lineTotal);
            taxAmount = taxAmount.add(itemTax);

            sale.getSaleItems().add(saleItem);
        }

        // sale.setSaleItems(saleItems);
        sale.setSubtotal(subtotal);
        sale.setTaxAmount(taxAmount);

        BigDecimal discount =
                request.getDiscountAmount() == null
                        ? BigDecimal.ZERO
                        : request.getDiscountAmount();

        sale.setDiscountAmount(discount);

        sale.setTotalAmount(
                calculateGrandTotal(
                        subtotal,
                        taxAmount,
                        discount));

        Sale updatedSale = saleRepository.save(sale);

        /*
        * Update Inventory
        * Create Stock Transactions
        */
        for (SaleItem item : updatedSale.getSaleItems()) {

                updateInventory(
                        item.getProduct(),
                        item.getQuantity());

                createStockTransaction(
                        item.getProduct(),
                        item.getQuantity(),
                        updatedSale.getSaleId());
        }

        return SaleMapper.toResponse(updatedSale);
    }

    private Sale findSaleById(Long saleId) {

        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.SALE_NOT_FOUND));
    }

    private Customer findCustomer(Long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.CUSTOMER_NOT_FOUND));
    }

    private Product findProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.PRODUCT_NOT_FOUND));
    }

    private Inventory findInventoryByProductId(Long productId) {

        return inventoryRepository.findByProductProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageConstants.INVENTORY_NOT_FOUND));
    }

    private void validateDuplicateInvoice(String invoiceNumber) {

        if (saleRepository.findByInvoiceNumber(invoiceNumber).isPresent()) {

            throw new DuplicateResourceException(
                    MessageConstants.SALE_ALREADY_EXISTS);
        }
    }

    private void validateDuplicateInvoiceForUpdate(Long saleId,
                                               String invoiceNumber) {

        saleRepository.findByInvoiceNumber(invoiceNumber)
                .ifPresent(existingSale -> {

                    if (!existingSale.getSaleId().equals(saleId)) {

                        throw new DuplicateResourceException(
                                MessageConstants.SALE_ALREADY_EXISTS);
                    }
                });
    }

    private BigDecimal calculateLineTotal(BigDecimal quantity,
                                      BigDecimal unitPrice) {

        return quantity.multiply(unitPrice);
    }

    private BigDecimal calculateTax(BigDecimal amount,
                                BigDecimal taxPercentage) {

        return amount.multiply(taxPercentage)
                .divide(BigDecimal.valueOf(100));
    }

    private BigDecimal calculateGrandTotal(BigDecimal subtotal,
                                       BigDecimal taxAmount,
                                       BigDecimal discount) {

        return subtotal
                .add(taxAmount)
                .subtract(discount);
    }

    private void validateStockAvailability(Product product,
                                       Inventory inventory,
                                       BigDecimal requestedQuantity) {

    BigDecimal availableQuantity =
            inventory.getQuantity()
                    .subtract(inventory.getReservedQuantity())
                    .subtract(inventory.getDamagedQuantity());

        if (availableQuantity.compareTo(requestedQuantity) < 0) {

                // throw new InsufficientStockException(
                //         MessageConstants.INSUFFICIENT_STOCK
                //                 + product.getProductName());
                
                throw new InsufficientStockException(
                        String.format(
                                "Insufficient stock for product '%s'. Available: %s, Requested: %s",
                                product.getProductName(),
                                availableQuantity,
                                requestedQuantity
                        )
                );
        }
    }

    private void updateInventory(Product product,
                             BigDecimal quantity) {

        Inventory inventory =
                findInventoryByProductId(product.getProductId());

        inventory.setQuantity(
                inventory.getQuantity().subtract(quantity));

        inventoryRepository.save(inventory);
    }

    private void createStockTransaction(Product product,
                                    BigDecimal quantity,
                                    Long saleId) {

        StockTransaction stockTransaction =
                StockTransaction.builder()
                        .product(product)
                        .transactionType(TransactionType.SALE)
                        .quantity(quantity)
                        .referenceId(saleId)
                        .remarks(MessageConstants.SALE_ENTRY)
                        .build();

        stockTransactionRepository.save(stockTransaction);
    }

    private void restoreInventory(Sale sale) {

        for (SaleItem saleItem : sale.getSaleItems()) {

                Inventory inventory = findInventoryByProductId(
                        saleItem.getProduct().getProductId());

                inventory.setQuantity(
                        inventory.getQuantity().add(saleItem.getQuantity()));

                inventoryRepository.save(inventory);
        }
    }

    private void deleteStockTransactions(Long saleId) {

        stockTransactionRepository.deleteByTransactionTypeAndReferenceId(
                TransactionType.SALE,
                saleId
        );
    }
}
