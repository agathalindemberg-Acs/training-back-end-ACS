package com.back.end.shoppingapi.repository;

import com.back.end.shoppingapi.dto.ShopReportDTO;
import com.back.end.shoppingapi.model.Shop;

import	java.time.LocalDate;
import	java.util.List;

public interface ReportRepository {
    List<Shop> getShopByFilters(
            LocalDate dataInicio,
            LocalDate dataFim,
            Float valorMinimo);

    ShopReportDTO getReportByDate(
            LocalDate dataInicio,
            LocalDate dataFim);
}
