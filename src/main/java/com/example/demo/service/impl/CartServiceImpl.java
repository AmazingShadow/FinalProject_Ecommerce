package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

}
