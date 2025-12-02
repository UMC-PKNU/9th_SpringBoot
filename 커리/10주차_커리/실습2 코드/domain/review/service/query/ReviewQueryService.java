package com.example.umc_9th_spring.domain.review.service.query;

import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResDTO.ReviewInfo> getReviewsFilter(Long userId, Long storeId, String rating);
    List<ReviewResDTO.ReviewInfo> getUserReviews(Long userId);
    List<ReviewResDTO.ReviewInfo> getStoreReviews(Long storeId);
}
