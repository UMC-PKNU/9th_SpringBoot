package com.example.umc9th_week5.domain.review.repository;

import com.example.umc9th_week5.domain.review.entity.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
//    StoreReview findById(Long id);

    @Query("select r from StoreReview r where r.id = :userId order by r.id desc")
    StoreReview findReview(@Param("userId") Long userId);
}
