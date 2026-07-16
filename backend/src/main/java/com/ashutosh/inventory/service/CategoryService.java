package com.ashutosh.inventory.service;
import com.ashutosh.inventory.dto.CategoryRequest;
import com.ashutosh.inventory.dto.CategoryResponse;

import java.util.List;
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    void deleteCategory(Long categoryId);
}
