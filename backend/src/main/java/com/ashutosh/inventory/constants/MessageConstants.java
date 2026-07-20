package com.ashutosh.inventory.constants;

public final class MessageConstants {

    private MessageConstants() {}

    //Common Messages
    public static final String VALIDATION_FAILED = "Validation failed.";
    public static final String INTERNAL_SERVER_ERROR = "Something went wrong. Please try again.";
    
    // Category Messages
    public static final String CATEGORY_CREATED =
            "Category created successfully.";

    public static final String CATEGORY_UPDATED =
            "Category updated successfully.";

    public static final String CATEGORY_DELETED =
            "Category deleted successfully.";

    public static final String CATEGORY_FETCHED =
            "Category fetched successfully.";

    public static final String CATEGORIES_FETCHED =
            "Categories fetched successfully.";
    
    public static final String CATEGORY_ALREADY_EXISTS = 
            "Category already exists with name: ";

    public static final String CATEGORY_NOT_FOUND = 
            "Category not found with ID: ";
}