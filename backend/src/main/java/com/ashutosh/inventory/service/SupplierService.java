package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.SupplierRequest;
import com.ashutosh.inventory.dto.SupplierResponse;

import java.util.List;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse getSupplierById(Long supplierId);

    SupplierResponse updateSupplier(Long supplierId,
                                    SupplierRequest request);

    void deleteSupplier(Long supplierId);

}