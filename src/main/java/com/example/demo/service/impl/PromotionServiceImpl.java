package com.example.demo.service.impl;

import com.example.demo.dto.PromotionsDTO;
import com.example.demo.entity.Promotions;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void savePromotion(PromotionsDTO promotionsDTO) {
        String normalized = Normalizer.normalize(promotionsDTO.getName(), Normalizer.Form.NFD); // loại bỏ các dấu trên chữ cái
        String name = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("\\s+","").replaceAll("-","").toLowerCase(); // loại bỏ các ký tự không mong muốn (ví dụ: khoảng trắng)
        System.out.println(promotionsDTO);
        Promotions promotions = Promotions.builder()
                .name(name)
                .promotionLimit(promotionsDTO.getPromotionLimit())
                .dateStart(promotionsDTO.getDateStart())
                .dateEnd(promotionsDTO.getDateEnd())
                .build();

        promotionRepository.save(promotions);
    }

    @Override
    public void deleteById(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public void save(Promotions promotions) {
        promotionRepository.save(promotions);
    }

    @Override
    public Optional<Promotions> findById(Long id) {
        return promotionRepository.findById(id);
    }

    @Override
    public Page<Promotions> getAllPromotions(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    @Override
    public List<Promotions> getAll() {
        return promotionRepository.findAll();
    }
}
