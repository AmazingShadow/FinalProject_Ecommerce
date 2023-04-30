package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

}
