package com.example.umc_9th_.domain.review.repository;

import com.example.umc_9th_.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    // DTO 대신 엔티티(Page<Review>) 반환 >> DTO변환은 Service or Converter가
    Page<Review> findMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable);
}