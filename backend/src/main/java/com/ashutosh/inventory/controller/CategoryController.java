package com.ashutosh.inventory.controller;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.category.CategoryRequest;
import com.ashutosh.inventory.dto.category.CategoryResponse;
import com.ashutosh.inventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ashutosh.inventory.constants.ApiPaths;
// import com.ashutosh.inventory.constants.AppConstants;
import com.ashutosh.inventory.constants.MessageConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
@Tag(
    name = "Category Management",
    description = "APIs for managing product categories"
)
@RestController
// @RequestMapping("/api/categories")
@RequestMapping(ApiPaths.CATEGORIES)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Operation(summary = "Create a new category")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Category created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Category already exists")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse category = categoryService.createCategory(request);

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .success(true)
                // .message("Category created successfully.")
                // .message(AppConstants.CATEGORY_CREATED)
                .message(MessageConstants.CATEGORY_CREATED)
                .data(category)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {

        List<CategoryResponse> categories = categoryService.getAllCategories();

        ApiResponse<List<CategoryResponse>> response = ApiResponse.<List<CategoryResponse>>builder()
                .success(true)
                // .message("Categories fetched successfully.")
                // .message(AppConstants.CATEGORIES_FETCHED)
                .message(MessageConstants.CATEGORIES_FETCHED)
                .data(categories)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(
            @PathVariable Long id) {

        CategoryResponse category = categoryService.getCategoryById(id);

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .success(true)
                // .message("Category fetched successfully.")
                // .message(AppConstants.CATEGORY_FETCHED)
                .message(MessageConstants.CATEGORY_FETCHED)
                .data(category)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse category = categoryService.updateCategory(id, request);

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .success(true)
                // .message("Category updated successfully.")
                // .message(AppConstants.CATEGORY_UPDATED)
                .message(MessageConstants.CATEGORY_UPDATED)
                .data(category)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        // ApiResponse<Void> response = ApiResponse.<Void>builder()
        //         .success(true)
        //         .message("Category deleted successfully.")
        //         .data(null)
        //         .build();

        // return ResponseEntity.ok(response);
        return ResponseEntity.noContent().build();
    }
}
