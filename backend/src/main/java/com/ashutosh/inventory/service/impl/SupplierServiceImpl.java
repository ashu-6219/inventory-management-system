package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.SupplierRequest;
import com.ashutosh.inventory.dto.SupplierResponse;
import com.ashutosh.inventory.entity.Supplier;
import com.ashutosh.inventory.enums.PaymentMode;
import com.ashutosh.inventory.exception.DuplicateResourceException;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.exception.BusinessValidationException;
import com.ashutosh.inventory.mapper.SupplierMapper;
import com.ashutosh.inventory.repository.SupplierRepository;
import com.ashutosh.inventory.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               SupplierMapper supplierMapper) {

        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {

        validateDuplicateSupplier(request);

        validatePaymentRules(request);

        Supplier supplier = supplierMapper.toEntity(request);

        Supplier savedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toResponse(savedSupplier);
    }

    private void validateDuplicateSupplier(SupplierRequest request) {

        if (supplierRepository.existsBySupplierName(request.getSupplierName())) {
            throw new DuplicateResourceException(
                    "Supplier already exists with name: " + request.getSupplierName());
        }

        if (supplierRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException(
                    "Phone number already exists: " + request.getPhone());
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && supplierRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists: " + request.getEmail());
        }

        if (request.getGstNumber() != null
                && !request.getGstNumber().isBlank()
                && supplierRepository.existsByGstNumber(request.getGstNumber())) {

            throw new DuplicateResourceException(
                    "GST number already exists: " + request.getGstNumber());
        }

    }

    private void validatePaymentRules(SupplierRequest request) {

        if (request.getPaymentMode() == PaymentMode.CREDIT) {

            if (request.getCreditDays() == null
                    || request.getCreditDays() <= 0) {

                throw new BusinessValidationException(
                        "Credit suppliers must have credit days greater than zero.");
            }

        } else {

            if (request.getCreditDays() != null
                    && request.getCreditDays() > 0) {

                throw new BusinessValidationException(
                        "Credit days should be zero for CASH or ADVANCE payment mode.");
            }

        }

    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toResponse)
                .toList();
    }

    @Override
    public SupplierResponse getSupplierById(Long supplierId) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.SUPPLIER_NOT_FOUND + supplierId));

        return supplierMapper.toResponse(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(Long supplierId,
                                        SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.SUPPLIER_NOT_FOUND + supplierId));

        validateDuplicateSupplierForUpdate(supplier, request);

        validatePaymentRules(request);

        // supplier.setSupplierName(request.getSupplierName());
        // supplier.setContactPerson(request.getContactPerson());
        // supplier.setPhone(request.getPhone());
        // supplier.setEmail(request.getEmail());
        // supplier.setGstNumber(request.getGstNumber());
        // supplier.setAddress(request.getAddress());
        // supplier.setPaymentMode(request.getPaymentMode());
        // supplier.setCreditDays(request.getCreditDays());

        // Supplier updatedSupplier = supplierRepository.save(supplier);

        // return supplierMapper.toResponse(updatedSupplier);

        validateDuplicateSupplierForUpdate(supplier, request);

        validatePaymentRules(request);

        supplierMapper.updateEntity(supplier, request);

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toResponse(updatedSupplier);
    }

    private void validateDuplicateSupplierForUpdate(
        Supplier supplier,
        SupplierRequest request) {

        if (!supplier.getSupplierName().equals(request.getSupplierName())
                && supplierRepository.existsBySupplierName(request.getSupplierName())) {

            throw new DuplicateResourceException(
                    MessageConstants.SUPPLIER_ALREADY_EXISTS
                            + request.getSupplierName());
        }

        if (!supplier.getPhone().equals(request.getPhone())
                && supplierRepository.existsByPhone(request.getPhone())) {

            throw new DuplicateResourceException(
                    MessageConstants.PHONE_ALREADY_EXISTS
                            + request.getPhone());
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && !request.getEmail().equals(supplier.getEmail())
                && supplierRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    MessageConstants.EMAIL_ALREADY_EXISTS
                            + request.getEmail());
        }

        if (request.getGstNumber() != null
                && !request.getGstNumber().isBlank()
                && !request.getGstNumber().equals(supplier.getGstNumber())
                && supplierRepository.existsByGstNumber(request.getGstNumber())) {

            throw new DuplicateResourceException(
                    MessageConstants.GST_ALREADY_EXISTS
                            + request.getGstNumber());
        }
    }

    @Override
    public void deleteSupplier(Long supplierId) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.SUPPLIER_NOT_FOUND + supplierId));

        supplierRepository.delete(supplier);
    }
}