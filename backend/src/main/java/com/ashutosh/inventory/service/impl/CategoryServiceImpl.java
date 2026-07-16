package com.ashutosh.inventory.service.impl;
import com.ashutosh.inventory.dto.CategoryRequest;
import com.ashutosh.inventory.dto.CategoryResponse;
import com.ashutosh.inventory.entity.Category;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.exception.DuplicateResourceException;
import com.ashutosh.inventory.mapper.CategoryMapper;
import com.ashutosh.inventory.repository.CategoryRepository;
import com.ashutosh.inventory.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new DuplicateResourceException("Category already exists with name: " + request.getCategoryName());
        }

        Category category = categoryMapper.toEntity(request);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
            .stream()
            .map(categoryMapper::toResponse)
            .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Category not found with ID: " + categoryId));

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId,
                                           CategoryRequest request) {

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Category not found with ID: " + categoryId));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Category not found with ID: " + categoryId));

        categoryRepository.delete(category);
    }

}
