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

        // Product Success Messages
        public static final String PRODUCT_CREATED = "Product created successfully.";
        public static final String PRODUCTS_FETCHED = "Products fetched successfully.";
        public static final String PRODUCT_FETCHED = "Product fetched successfully.";
        public static final String PRODUCT_UPDATED = "Product updated successfully.";
        public static final String PRODUCT_DELETED = "Product deleted successfully.";

        // Product Error Messages
        public static final String PRODUCT_NOT_FOUND = "Product not found with ID: ";
        public static final String PRODUCT_SKU_ALREADY_EXISTS = "SKU already exists: ";
        public static final String PRODUCT_BARCODE_ALREADY_EXISTS = "Barcode already exists: ";
        public static final String INVALID_PRODUCT_PRICING =
                "Selling price cannot be less than cost price.";

        
        // Customer Success Messages
        public static final String CUSTOMER_CREATED =
                "Customer created successfully.";

        public static final String CUSTOMERS_FETCHED =
                "Customers fetched successfully.";

        public static final String CUSTOMER_FETCHED =
                "Customer fetched successfully.";

        public static final String CUSTOMER_UPDATED =
                "Customer updated successfully.";

        public static final String CUSTOMER_DELETED =
                "Customer deleted successfully.";
        
        // Customer Error Messages
        public static final String CUSTOMER_NOT_FOUND =
                "Customer not found with ID: ";

        public static final String CUSTOMER_ALREADY_EXISTS =
                "Customer already exists: ";

        public static final String CUSTOMER_PHONE_ALREADY_EXISTS =
                "Phone number already exists: ";

        public static final String CUSTOMER_EMAIL_ALREADY_EXISTS =
                "Email already exists: ";

        public static final String CUSTOMER_GST_ALREADY_EXISTS =
                "GST number already exists: ";


        // Purchase Success Messages
        public static final String PURCHASE_CREATED =
                "Purchase created successfully.";

        public static final String PURCHASE_UPDATED =
                "Purchase updated successfully.";

        public static final String PURCHASE_DELETED =
                "Purchase deleted successfully.";

        public static final String PURCHASE_FETCHED =
                "Purchase retrieved successfully.";

        public static final String PURCHASES_FETCHED =
                "Purchases retrieved successfully.";


        // Purchase Error Messages
        public static final String PURCHASE_NOT_FOUND =
                "Purchase not found.";

        public static final String PURCHASE_ALREADY_EXISTS =
                "Purchase with the given invoice number already exists.";

        public static final String INVALID_PURCHASE =
                "Invalid purchase request.";

        public static final String PURCHASE_ITEMS_EMPTY =
                "Purchase must contain at least one item.";


        // Inventory Message
        public static final String INVENTORY_NOT_FOUND = "Inventory not found.";
        public static final String INVENTORY_FETCHED_SUCCESSFULLY =
                "Inventory retrieved successfully.";

        // Stock Transaction Messages
        public static final String STOCK_TRANSACTION_NOT_FOUND =
                "Stock Transaction not found.";

        public static final String STOCK_TRANSACTION_FETCHED_SUCCESSFULLY =
                "Stock Transaction fetched successfully.";

        public static final String STOCK_TRANSACTIONS_FETCHED_SUCCESSFULLY =
                "Stock Transactions fetched successfully.";
        
        public static final String PURCHASE_ENTRY = "Purchase Entry";


        // Sale Messages
        public static final String SALE_NOT_FOUND = "Sale not found.";

        public static final String SALE_ALREADY_EXISTS =
                "Sale with the given invoice number already exists.";

        public static final String SALE_CREATED_SUCCESSFULLY =
                "Sale created successfully.";

        public static final String SALE_UPDATED_SUCCESSFULLY =
                "Sale updated successfully.";

        public static final String SALE_DELETED_SUCCESSFULLY =
                "Sale deleted successfully.";

        public static final String SALE_FETCHED_SUCCESSFULLY =
                "Sale fetched successfully.";

        public static final String SALES_FETCHED_SUCCESSFULLY =
                "Sales fetched successfully.";

        public static final String SALE_ENTRY =
                "Sale Entry";

        public static final String INSUFFICIENT_STOCK =
                "Insufficient stock available for product: ";


        // Role Messages
        public static final String ROLE_CREATED = "Role created successfully.";
        public static final String ROLE_UPDATED = "Role updated successfully.";
        public static final String ROLE_DELETED = "Role deleted successfully.";
        public static final String ROLE_RETRIEVED = "Role retrieved successfully.";
        public static final String ROLES_RETRIEVED = "Roles retrieved successfully.";
}