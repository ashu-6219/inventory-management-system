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

        // Supplier Validation Messages
        public static final String SUPPLIER_NAME_REQUIRED =
                "Supplier name is required.";

        public static final String SUPPLIER_NAME_SIZE =
                "Supplier name must be between 3 and 150 characters.";

        public static final String CONTACT_PERSON_SIZE =
                "Contact person name cannot exceed 100 characters.";

        public static final String PHONE_REQUIRED =
                "Phone number is required.";

        // public static final String INVALID_PHONE =
        //         "Phone number must contain exactly 10 digits.";

        // public static final String INVALID_EMAIL =
        //         "Please enter a valid email address.";

        public static final String GST_NUMBER_SIZE =
                "GST number must be exactly 15 characters.";

        public static final String INVALID_GST =
                "Please enter a valid GST number.";

        public static final String ADDRESS_SIZE =
                "Address cannot exceed 300 characters.";

        public static final String PAYMENT_MODE_REQUIRED =
                "Payment mode is required.";

        public static final String CREDIT_DAYS_INVALID =
                "Credit days cannot be negative.";


        // Product Validation Messages
        public static final String PRODUCT_NAME_REQUIRED =
                "Product name is required.";

        public static final String PRODUCT_NAME_SIZE =
                "Product name must be between 3 and 150 characters.";

        public static final String SKU_REQUIRED =
                "SKU is required.";

        public static final String SKU_SIZE =
                "SKU cannot exceed 50 characters.";

        public static final String BARCODE_SIZE =
                "Barcode cannot exceed 50 characters.";

        public static final String CATEGORY_ID_REQUIRED =
                "Category ID is required.";

        public static final String SUPPLIER_ID_REQUIRED =
                "Supplier ID is required.";

        public static final String COST_PRICE_REQUIRED =
                "Cost price is required.";

        public static final String SELLING_PRICE_REQUIRED =
                "Selling price is required.";

        public static final String TAX_PERCENTAGE_INVALID =
                "Tax percentage cannot be negative.";

        public static final String UNIT_REQUIRED =
                "Unit is required.";

        public static final String REORDER_LEVEL_INVALID =
                "Reorder level cannot be negative.";

        public static final String DESCRIPTION_SIZE =
                "Description cannot exceed 500 characters.";

        
        // Customer Validation Messages
        public static final String CUSTOMER_NAME_REQUIRED =
                "Customer name is required.";

        public static final String CUSTOMER_NAME_SIZE =
                "Customer name must be between 3 and 150 characters.";

        public static final String CUSTOMER_PHONE_REQUIRED =
                "Phone number is required.";

        public static final String CUSTOMER_INVALID_PHONE =
                "Phone number must be a valid 10-digit Indian mobile number.";

        public static final String CUSTOMER_EMAIL_REQUIRED =
                "Email is required.";

        public static final String CUSTOMER_INVALID_EMAIL =
                "Please provide a valid email address.";

        public static final String CUSTOMER_ADDRESS_REQUIRED =
                "Address is required.";

        public static final String CUSTOMER_ADDRESS_SIZE =
                "Address cannot exceed 300 characters.";

        public static final String CUSTOMER_GST_REQUIRED =
                "GST number is required.";

        public static final String CUSTOMER_GST_SIZE =
                "GST number must be exactly 15 characters.";

        public static final String CUSTOMER_INVALID_GST =
                "Please provide a valid GST number.";


        // Purchase Validation Messages
        // public static final String SUPPLIER_ID_REQUIRED =
        //         "Supplier ID is required.";

        public static final String INVOICE_NUMBER_REQUIRED =
                "Invoice number is required.";

        public static final String INVOICE_NUMBER_SIZE =
                "Invoice number must not exceed 100 characters.";

        public static final String PURCHASE_DATE_REQUIRED =
                "Purchase date is required.";

        public static final String PURCHASE_ITEMS_REQUIRED =
                "At least one purchase item is required.";

        public static final String REMARKS_SIZE =
                "Remarks must not exceed 300 characters.";

        
        // Purchase Item Validation Messages
        public static final String PRODUCT_ID_REQUIRED =
                "Product ID is required.";

        public static final String QUANTITY_REQUIRED =
                "Quantity is required.";

        // public static final String INVALID_QUANTITY =
        //         "Quantity must be greater than zero.";

        public static final String UNIT_PRICE_REQUIRED =
                "Unit price is required.";

        public static final String INVALID_UNIT_PRICE =
                "Unit price must be greater than zero.";

        public static final String TAX_PERCENTAGE_REQUIRED =
                "Tax percentage is required.";

        public static final String INVALID_TAX_PERCENTAGE =
                "Tax percentage cannot be negative.";

        // Sale Validation Messages
        public static final String CUSTOMER_ID_REQUIRED = "Customer ID is required.";

        public static final String SALE_DATE_REQUIRED = "Sale date is required.";

        public static final String SALE_ITEMS_REQUIRED = "At least one sale item is required.";
}
