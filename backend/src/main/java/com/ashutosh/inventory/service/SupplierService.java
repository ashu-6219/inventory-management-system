package com.ashutosh.inventory.service;

import java.util.List;

import com.ashutosh.inventory.dto.supplier.SupplierRequest;
import com.ashutosh.inventory.dto.supplier.SupplierResponse;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse getSupplierById(Long supplierId);

    SupplierResponse updateSupplier(Long supplierId,
                                    SupplierRequest request);

    void deleteSupplier(Long supplierId);

}