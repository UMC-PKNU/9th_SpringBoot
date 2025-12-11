package com.example.umc9th_week5.domain.member.repository;

import com.example.umc9th_week5.domain.member.entity.MemberFoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodCategoryRepository extends JpaRepository<MemberFoodCategory, Long> {
}
