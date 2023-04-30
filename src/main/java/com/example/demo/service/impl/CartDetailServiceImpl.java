package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.DetailCart;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.DetailCartRepository;
import com.example.demo.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private DetailCartRepository detailCartRepository;

    @Override
    public void save(DetailCart detailCart) {
        detailCartRepository.save(detailCart);
    }

    @Override
    public DetailCart findDetailCartByCartIdAndProductId(Cart cart, Product product) {
        return detailCartRepository.findDetailCartByCartIdAndProductId(cart, product);
    }

    @Override
    public List<Product> findAllProduct(Cart cart) {
        return detailCartRepository.findAllProduct(cart);
    }

    @Override
    public List<DetailCart> findDetailCart(Cart cart) {
        return detailCartRepository.findDetailCart(cart);
    }

    @Override
    public void deleteById(Long id) {
        detailCartRepository.deleteById(id);
    }
}
