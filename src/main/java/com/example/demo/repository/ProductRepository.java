package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select p from Product as p where p.category = :category")
    List<Product> findProducts(@Param("category")Category category);

    @Query("SELECT p FROM Product p WHERE ((:brand IS NULL OR :brand = '' OR p.brand = :brand) AND (:color IS NULL OR :color = '' OR p.color = :color) AND (:category IS NULL OR p.category = :category))")
    Page<Product> findProductsBy(@Param("brand") String brand, @Param("color") String color, @Param("category") Category category, Pageable pageable);
}
