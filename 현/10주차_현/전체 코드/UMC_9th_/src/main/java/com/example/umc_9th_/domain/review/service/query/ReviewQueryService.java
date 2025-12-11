package com.example.umc_9th_.domain.review.service.query;

import com.example.umc_9th_.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    Page<Review> getMyReviews(Long userId, String storeName, Integer ratingRange, Pageable pageable);
}