package com.example.demo.service;

import com.example.demo.entity.Cart;

import java.util.Optional;

public interface CartService {
    void save(Cart cart);
    Optional<Cart> findById(Long id);

    void deleteById(Long id);
}
