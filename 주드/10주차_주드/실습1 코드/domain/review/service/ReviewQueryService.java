package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewQueryService {
    Page<Review> getMyReviewsByStore(Long memberId, Long storeId, Integer page);
    List<Review> findReviewsByStoreAndRating(Long memberId, String storeName, Long rating);
    Review createReview(Long userId, Long missionId, ReviewReqDto.ReviewCreateDto request);
}
