package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);

    void save(CategoryDTO categoryDTO);
    Page<Category> findAll(Pageable page);
    void updateCategory(Category category);
    void deleteById(Long id);
    List<Category> getAll();
}
