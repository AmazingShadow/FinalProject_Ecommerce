package com.example.demo.repository;

import com.example.demo.entity.DetailOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailOrdersRepository extends JpaRepository<DetailOrders, Long> {
}
