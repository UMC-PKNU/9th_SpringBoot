package com.example.umc_9th_.domain.review.repository;

import com.example.umc_9th_.domain.review.dto.MyReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<MyReviewResponse> findMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable);
}
