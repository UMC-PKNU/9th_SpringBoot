package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> findReviewsByStoreAndRating(Long memberId, String storeName, Long rating);
}
