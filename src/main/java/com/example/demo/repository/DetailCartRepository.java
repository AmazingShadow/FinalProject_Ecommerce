package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.DetailCart;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCartRepository extends JpaRepository<DetailCart, Long> {
    DetailCart findDetailCartByCartIdAndProductId(Cart cart, Product product);

    @Query("Select d.productId from DetailCart as d where d.cartId = :cartId")
    List<Product> findAllProduct(@Param("cartId") Cart cart);

    @Query("Select s from DetailCart s where s.cartId = :cartId")
    List<DetailCart> findDetailCart(@Param("cartId") Cart cart);
}
