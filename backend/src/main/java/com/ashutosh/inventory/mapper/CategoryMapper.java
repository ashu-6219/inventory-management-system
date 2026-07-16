package com.ashutosh.inventory.mapper;
import com.ashutosh.inventory.dto.CategoryRequest;
import com.ashutosh.inventory.dto.CategoryResponse;
import com.ashutosh.inventory.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest request) {

        return Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .isActive(true)
                .build();
    }

    public CategoryResponse toResponse(Category category) {

        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .isActive(category.getIsActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
