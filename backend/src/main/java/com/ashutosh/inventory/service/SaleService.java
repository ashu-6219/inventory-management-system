package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.sale.SaleRequest;
import com.ashutosh.inventory.dto.sale.SaleResponse;

import java.util.List;

public interface SaleService {

    SaleResponse createSale(SaleRequest request);

    List<SaleResponse> getAllSales();

    SaleResponse getSaleById(Long saleId);

    SaleResponse updateSale(Long saleId, SaleRequest request);

    void deleteSale(Long saleId);

}