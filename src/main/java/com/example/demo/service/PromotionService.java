package com.example.demo.service;

import com.example.demo.dto.PromotionsDTO;
import com.example.demo.entity.Promotions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PromotionService {
    void savePromotion(PromotionsDTO promotionsDTO);
    void deleteById(Long id);
    void save(Promotions promotions);
    Optional<Promotions> findById(Long id);
    Page<Promotions> getAllPromotions(Pageable pageable);
    List<Promotions> getAll();
}
