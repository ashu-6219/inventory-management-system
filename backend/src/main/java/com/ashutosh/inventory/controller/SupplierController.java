package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.SupplierRequest;
import com.ashutosh.inventory.dto.SupplierResponse;
import com.ashutosh.inventory.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.SUPPLIERS)
@Tag(name = "Supplier Management", description = "APIs for managing suppliers")
public class SupplierController {

        private final SupplierService supplierService;

        public SupplierController(SupplierService supplierService) {
                this.supplierService = supplierService;
        }

        @PostMapping
        @Operation(summary = "Create a new supplier",
                description = "Creates a supplier after validating duplicate records and business rules."
        )
        @ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "201", 
                        description = "Supplier created successfully"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "400", 
                        description = "Validation or business rule violation"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "409", 
                        description = "Duplicate supplier details"
                )
        })
        public ResponseEntity<ApiResponse<SupplierResponse>> createSupplier(
                @Valid @RequestBody SupplierRequest request) {

                SupplierResponse response = supplierService.createSupplier(request);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ApiResponse.<SupplierResponse>builder()
                                .success(true)
                                .message(MessageConstants.SUPPLIER_CREATED)
                                .data(response)
                                .build());
        }

        @GetMapping
        @Operation(summary = "Get all suppliers",
                description = "Retrieve all suppliers."
        )
        @ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "200",
                        description = "Suppliers fetched successfully"
                )
        })
        public ResponseEntity<ApiResponse<List<SupplierResponse>>> getAllSuppliers() {

                List<SupplierResponse> response = supplierService.getAllSuppliers();

                return ResponseEntity.ok(
                        ApiResponse.<List<SupplierResponse>>builder()
                                .success(true)
                                .message(MessageConstants.SUPPLIERS_FETCHED)
                                .data(response)
                                .build());
        }

        @GetMapping("/{supplierId}")
        @Operation(summary = "Get supplier by ID",
                description = "Retrieves supplier details using supplier ID."
        )
        @ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "200",
                        description = "Supplier found"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "404",
                        description = "Supplier not found"
                )
        })
        public ResponseEntity<ApiResponse<SupplierResponse>> getSupplierById(
                @PathVariable Long supplierId) {

                SupplierResponse response = supplierService.getSupplierById(supplierId);

                return ResponseEntity.ok(
                        ApiResponse.<SupplierResponse>builder()
                                .success(true)
                                .message(MessageConstants.SUPPLIER_FETCHED)
                                .data(response)
                                .build());
        }

        @PutMapping("/{supplierId}")
        @Operation(summary = "Update supplier",
                description = "Updates supplier information."
        )
        @ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "200",
                        description = "Supplier updated successfully"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "400",
                        description = "Validation failed"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "404",
                        description = "Supplier not found"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "409",
                        description = "Duplicate supplier details"
                )
        })
        public ResponseEntity<ApiResponse<SupplierResponse>> updateSupplier(
                @PathVariable Long supplierId,
                @Valid @RequestBody SupplierRequest request) {

                SupplierResponse response =
                        supplierService.updateSupplier(supplierId, request);

                return ResponseEntity.ok(
                        ApiResponse.<SupplierResponse>builder()
                                .success(true)
                                .message(MessageConstants.SUPPLIER_UPDATED)
                                .data(response)
                                .build());
        }

        @DeleteMapping("/{supplierId}")
        @Operation(summary = "Delete supplier",
                 description = "Deletes supplier by ID."
        )
        @ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "204",
                        description = "Supplier deleted successfully"
                ),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        responseCode = "404",
                        description = "Supplier not found"
                )
        })
        public ResponseEntity<Void> deleteSupplier(
                @PathVariable Long supplierId) {

                supplierService.deleteSupplier(supplierId);

                return ResponseEntity.noContent().build();
        }

}