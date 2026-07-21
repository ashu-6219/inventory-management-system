package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.product.ProductRequest;
import com.ashutosh.inventory.dto.product.ProductResponse;
import com.ashutosh.inventory.entity.Category;
import com.ashutosh.inventory.entity.Product;
import com.ashutosh.inventory.entity.Supplier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request,
                            Category category,
                            Supplier supplier) {

        return Product.builder()
                .productName(request.getProductName())
                .sku(request.getSku())
                .barcode(request.getBarcode())
                .category(category)
                .supplier(supplier)
                .costPrice(request.getCostPrice())
                .sellingPrice(request.getSellingPrice())
                .taxPercentage(
                        request.getTaxPercentage() != null
                                ? request.getTaxPercentage()
                                : BigDecimal.ZERO)
                .unit(request.getUnit())
                .reorderLevel(
                        request.getReorderLevel() != null
                                ? request.getReorderLevel()
                                : 10)
                .description(request.getDescription())
                .isActive(true)
                .build();
    }

    public ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .sku(product.getSku())
                .barcode(product.getBarcode())

                .categoryId(product.getCategory().getCategoryId())
                .categoryName(product.getCategory().getCategoryName())

                .supplierId(product.getSupplier().getSupplierId())
                .supplierName(product.getSupplier().getSupplierName())

                .costPrice(product.getCostPrice())
                .sellingPrice(product.getSellingPrice())
                .taxPercentage(product.getTaxPercentage())
                .unit(product.getUnit())
                .reorderLevel(product.getReorderLevel())
                .description(product.getDescription())
                .isActive(product.getIsActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public void updateEntity(Product product,
                             ProductRequest request,
                             Category category,
                             Supplier supplier) {

        product.setProductName(request.getProductName());
        product.setSku(request.getSku());
        product.setBarcode(request.getBarcode());

        product.setCategory(category);
        product.setSupplier(supplier);

        product.setCostPrice(request.getCostPrice());
        product.setSellingPrice(request.getSellingPrice());

        product.setTaxPercentage(
                request.getTaxPercentage() != null
                        ? request.getTaxPercentage()
                        : BigDecimal.ZERO);

        product.setUnit(request.getUnit());

        product.setReorderLevel(
                request.getReorderLevel() != null
                        ? request.getReorderLevel()
                        : 10);

        product.setDescription(request.getDescription());
    }

}
