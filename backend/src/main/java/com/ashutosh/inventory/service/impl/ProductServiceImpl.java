package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.product.ProductRequest;
import com.ashutosh.inventory.dto.product.ProductResponse;
import com.ashutosh.inventory.entity.Category;
import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.entity.Supplier;
import com.ashutosh.inventory.exception.BusinessValidationException;
import com.ashutosh.inventory.exception.DuplicateResourceException;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.ProductMapper;
import com.ashutosh.inventory.repository.CategoryRepository;
import com.ashutosh.inventory.repository.ProductRepository;
import com.ashutosh.inventory.repository.SupplierRepository;
import com.ashutosh.inventory.service.ProductService;
import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              SupplierRepository supplierRepository,
                              ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        validateDuplicateProduct(request);

        Category category = getCategory(request.getCategoryId());

        Supplier supplier = getSupplier(request.getSupplierId());

        validateProductPricing(request);

        Product product = productMapper.toEntity(
                request,
                category,
                supplier
        );

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    private void validateDuplicateProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {

            throw new DuplicateResourceException(
                    MessageConstants.PRODUCT_SKU_ALREADY_EXISTS
                            + request.getSku());
        }

        if (request.getBarcode() != null
                && !request.getBarcode().isBlank()
                && productRepository.existsByBarcode(request.getBarcode())) {

            throw new DuplicateResourceException(
                    MessageConstants.PRODUCT_BARCODE_ALREADY_EXISTS
                            + request.getBarcode());
        }

    }

    private Category getCategory(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.CATEGORY_NOT_FOUND + categoryId));
    }

    private Supplier getSupplier(Long supplierId) {

        return supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.SUPPLIER_NOT_FOUND + supplierId));
    }

    private void validateProductPricing(ProductRequest request) {

        if (request.getSellingPrice()
                .compareTo(request.getCostPrice()) < 0) {

            throw new BusinessValidationException(
                    MessageConstants.INVALID_PRODUCT_PRICING);
        }

    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product = findProductById(productId);

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long productId,
                                        ProductRequest request) {

        Product product = findProductById(productId);

        validateDuplicateProductForUpdate(product, request);

        Category category = getCategory(request.getCategoryId());

        Supplier supplier = getSupplier(request.getSupplierId());

        validateProductPricing(request);

        productMapper.updateEntity(
                product,
                request,
                category,
                supplier
        );

        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {

        Product product = findProductById(productId);

        productRepository.delete(product);

    }

    private Product findProductById(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.PRODUCT_NOT_FOUND
                                        + productId));
    }

    private boolean hasBarcodeChanged(Product product,
                                  ProductRequest request) {

        return !java.util.Objects.equals(
                product.getBarcode(),
                request.getBarcode()
        );
    }

    private void validateDuplicateProductForUpdate(Product product,
                                               ProductRequest request) {

        if (!product.getSku().equals(request.getSku())
                && productRepository.existsBySku(request.getSku())) {

            throw new DuplicateResourceException(
                    MessageConstants.PRODUCT_SKU_ALREADY_EXISTS
                            + request.getSku());
        }

        // String existingBarcode = product.getBarcode();
        // String newBarcode = request.getBarcode();

        // if (newBarcode != null && !newBarcode.isBlank()) {

        //     if ((existingBarcode == null
        //             || !existingBarcode.equals(newBarcode))
        //             && productRepository.existsByBarcode(newBarcode)) {

        //         throw new DuplicateResourceException(
        //                 MessageConstants.PRODUCT_BARCODE_ALREADY_EXISTS
        //                         + newBarcode);
        //     }
        // }

        if (hasBarcodeChanged(product, request)
                && request.getBarcode() != null
                && !request.getBarcode().isBlank()
                && productRepository.existsByBarcode(request.getBarcode())) {

            throw new DuplicateResourceException(
                    MessageConstants.PRODUCT_BARCODE_ALREADY_EXISTS
                            + request.getBarcode());
        }

    }
}
