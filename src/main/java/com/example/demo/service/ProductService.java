package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable page);
    Optional<Product> findById(Long id);
    Product insertProduct(Product product);
    void saveProductToDatabase(MultipartFile file, ProductDTO product);
    boolean removeById(Long id);
    boolean save(Product product);

    List<Product> findProducts(Category category);
//    List<Product> findProductsBy(String brand, String color);
    Page<Product> findAllProductSearch(String brand, String color, Category category, Pageable pageable);
    List<Product> getAll();
}
