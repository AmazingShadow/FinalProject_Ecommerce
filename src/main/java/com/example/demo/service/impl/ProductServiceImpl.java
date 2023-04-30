package com.example.demo.service.impl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


//    @Override
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }

    @Override
    public Page<Product> findAll(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public boolean removeById(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean save(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> findProducts(Category category) {
        return productRepository.findProducts(category);
    }

//    @Override
//    public List<Product> findProductsBy(String brand, String color) {
//        return productRepository.findProductsBy(brand, color);
//    }

    @Override
    public Page<Product> findAllProductSearch(String brand, String color, Category category,Pageable pageable) {
        return productRepository.findProductsBy(brand, color, category,pageable);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void saveProductToDatabase(MultipartFile file, ProductDTO product) {
        Product p;
        Date now = new Date();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p = Product.builder()
                    .brand(product.getBrand())
                    .color(product.getColor())
                    .description(product.getDescription())
                    .image(Base64.getEncoder().encodeToString(file.getBytes()))
                    .name(product.getName())
                    .title(product.getTitle())
                    .information(product.getInformation())
                    .config(product.getConfig())
                    .size(product.getSize())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .createdAt(now)
                    .updatedAt(now)
                    .category(Category.builder().id(product.getCategoryId()).build())
                    .build();
            productRepository.save(p);
//            p.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
