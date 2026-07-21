package com.ashutosh.inventory.constants;

public final class MessageConstants {


        private MessageConstants() {}

        //Common Messages
        public static final String VALIDATION_FAILED = "Validation failed.";
        public static final String INTERNAL_SERVER_ERROR = "Something went wrong. Please try again.";
        
        // Category Messages
        public static final String CATEGORY_CREATED = "Category created successfully.";

        public static final String CATEGORY_UPDATED = "Category updated successfully.";

        public static final String CATEGORY_DELETED = "Category deleted successfully.";

        public static final String CATEGORY_FETCHED = "Category fetched successfully.";

        public static final String CATEGORIES_FETCHED = "Categories fetched successfully.";
        
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists with name: ";

        public static final String CATEGORY_NOT_FOUND = "Category not found with ID: ";

        // Supplier Success Messages
        public static final String SUPPLIER_CREATED = "Supplier created successfully.";
        public static final String SUPPLIER_UPDATED = "Supplier updated successfully.";
        public static final String SUPPLIERS_FETCHED = "Suppliers fetched successfully.";
        public static final String SUPPLIER_FETCHED = "Supplier fetched successfully.";
        public static final String SUPPLIER_DELETED = "Supplier deleted successfully.";

        // Supplier Error Messages
        public static final String SUPPLIER_ALREADY_EXISTS = "Supplier already exists with name: ";
        public static final String PHONE_ALREADY_EXISTS = "Phone number already exists: ";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists: ";
        public static final String GST_ALREADY_EXISTS = "GST number already exists: ";
        public static final String SUPPLIER_NOT_FOUND = "Supplier not found with ID: ";
        public static final String CREDIT_DAYS_REQUIRED = "Credit suppliers must have credit days greater than zero.";
        public static final String CREDIT_DAYS_NOT_ALLOWED = "Credit days should be zero for CASH or ADVANCE payment mode.";
}