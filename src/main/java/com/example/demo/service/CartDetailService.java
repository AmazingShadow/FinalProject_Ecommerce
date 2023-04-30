package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.DetailCart;
import com.example.demo.entity.Product;

import java.util.List;

public interface CartDetailService {
    void save(DetailCart detailCart);
    DetailCart findDetailCartByCartIdAndProductId(Cart cart, Product product);
    List<Product> findAllProduct(Cart cart);
    List<DetailCart> findDetailCart(Cart cart);
    void deleteById(Long id);
}
