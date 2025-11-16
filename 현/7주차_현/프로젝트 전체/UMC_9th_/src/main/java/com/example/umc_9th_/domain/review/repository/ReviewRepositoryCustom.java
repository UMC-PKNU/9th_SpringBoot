package com.example.umc_9th_.domain.review.repository;

import com.example.umc_9th_.domain.review.dto.ReviewResponse.MyReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<MyReviewDTO> findMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable);
}
