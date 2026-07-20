package com.ashutosh.inventory.constants;

public final class ValidationMessages {

    private ValidationMessages(){
        // Prevent instantiation
    }

    // Common Validation Messages
    public static final String FIELD_REQUIRED = "This field is required.";
    public static final String INVALID_EMAIL = "Please enter a valid email address.";
    public static final String INVALID_PHONE = "Please enter a valid phone number.";
    public static final String INVALID_PRICE = "Price must be greater than or equal to zero.";
    public static final String INVALID_QUANTITY = "Quantity must be greater than or equal to zero.";

    // Category Validation Messages
    public static final String CATEGORY_NAME_REQUIRED = "Category name is required.";
    public static final String CATEGORY_NAME_SIZE =
            "Category name must be between 3 and 100 characters.";

    public static final String CATEGORY_DESCRIPTION_SIZE =
            "Description cannot exceed 255 characters.";
}
