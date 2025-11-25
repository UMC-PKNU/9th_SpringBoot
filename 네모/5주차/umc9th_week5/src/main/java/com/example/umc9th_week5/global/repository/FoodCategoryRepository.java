package com.example.umc9th_week5.global.repository;

import com.example.umc9th_week5.global.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
    Optional<FoodCategory> findById(Long id);
}
