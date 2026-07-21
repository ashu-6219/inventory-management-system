package com.ashutosh.inventory.service;
import java.util.List;

import com.ashutosh.inventory.dto.category.CategoryRequest;
import com.ashutosh.inventory.dto.category.CategoryResponse;
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    void deleteCategory(Long categoryId);
}
