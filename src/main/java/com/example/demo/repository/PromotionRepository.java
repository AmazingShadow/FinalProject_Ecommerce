package com.example.demo.repository;

import com.example.demo.entity.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions, Long> {
}
