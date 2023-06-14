package com.back.end.shoppingapi.repository;

import java.time.LocalDateTime;
import	java.util.Date;
import	java.util.List;

import com.back.end.shoppingapi.model.Shop;
import org.springframework.context.annotation.Primary;
import	org.springframework.data.jpa.repository.JpaRepository;
import	org.springframework.stereotype.Repository;

@Primary
public interface ShopRepository extends JpaRepository<Shop,Long>, ReportRepository{
    public List<Shop> findAllByUserIdentifier(String userIdentifier);
    public List<Shop> findAllByTotalGreaterThan(Float Total);
    List<Shop> findAllByDateGreaterThan(LocalDateTime date);
}
