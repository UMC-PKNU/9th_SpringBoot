package com.example.umc_9th_.domain.store.repository;

import com.example.umc_9th_.domain.store.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}