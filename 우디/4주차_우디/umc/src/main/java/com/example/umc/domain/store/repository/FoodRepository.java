package com.example.umc.domain.store.repository;

import com.example.umc.domain.store.mapping.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
