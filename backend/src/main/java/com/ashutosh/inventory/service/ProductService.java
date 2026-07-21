package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.product.ProductRequest;
import com.ashutosh.inventory.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    ProductResponse updateProduct(Long productId, ProductRequest request);

    void deleteProduct(Long productId);
}
