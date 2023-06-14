package com.back.end.productapi.repository;

import com.back.end.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import	org.springframework.stereotype.Repository;

import	java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value ="select p"
            + "from product p"
            + "join category c on p.category.id = c.id"
            + "where c.id = :categoryId",nativeQuery = true)
    public List<Product> getProductByCategory(
            @Param("categoryId") long categoryId);
    public Product findByProductIdentifier(
            String productIdentifier);

}
