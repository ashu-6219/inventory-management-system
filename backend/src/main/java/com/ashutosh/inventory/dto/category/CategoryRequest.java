package com.ashutosh.inventory.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.ashutosh.inventory.constants.ValidationMessages;

@Data
public class CategoryRequest {

    // @NotBlank(message = "Category name is required.")
    // @Size(max = 100, message = "Category name cannot exceed 100 characters.")
    // private String categoryName;

    // @Size(max = 500, message = "Description cannot exceed 500 characters.")
    // private String description;

    @NotBlank(message = ValidationMessages.CATEGORY_NAME_REQUIRED)
    @Size(
            min = 3,
            max = 100,
            message = ValidationMessages.CATEGORY_NAME_SIZE
    )
    private String categoryName;

    @Size(
            max = 255,
            message = ValidationMessages.CATEGORY_DESCRIPTION_SIZE
    )
    private String description;
    
}
