package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.product.ProductRequest;
import com.ashutosh.inventory.dto.product.ProductResponse;
import com.ashutosh.inventory.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.PRODUCTS)
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create Product")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Product created successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Validation failed"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Category or Supplier not found"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "409",
                description = "Duplicate SKU or Barcode"
        )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message(MessageConstants.PRODUCT_CREATED)
                        .data(response)
                        .build());
    }

    @Operation(summary = "Get All Products")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllProducts() {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message(MessageConstants.PRODUCTS_FETCHED)
                        .data(productService.getAllProducts())
                        .build()
        );
    }

    @Operation(summary = "Get Product By ID")
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long productId) {

        ProductResponse response =
                productService.getProductById(productId);

        return ResponseEntity.ok(
                ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message(MessageConstants.PRODUCT_FETCHED)
                        .data(response)
                        .build()
        );
    }

    @Operation(summary = "Update Product")
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response =
                productService.updateProduct(productId, request);

        return ResponseEntity.ok(
                ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message(MessageConstants.PRODUCT_UPDATED)
                        .data(response)
                        .build()
        );
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }
}
