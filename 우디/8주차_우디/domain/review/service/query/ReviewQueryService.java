package com.example.umc.domain.review.service.query;

import com.example.umc.domain.review.dto.res.ReviewResDto;

import java.util.List;

public interface ReviewQueryService {

    void updateReview(Long reviewId, Double newRating, String newContent);

    List<ReviewResDto.ReviewInfo> getReviews(Long memberId, String storeName, Double rating);
}
