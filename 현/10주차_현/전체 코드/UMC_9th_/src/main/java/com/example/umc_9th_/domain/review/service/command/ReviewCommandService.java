package com.example.umc_9th_.domain.review.service.command;

import com.example.umc_9th_.domain.review.dto.ReviewRequest;
import com.example.umc_9th_.domain.review.entity.Review;

public interface ReviewCommandService {
    Review writeReview(ReviewRequest.WriteReviewDTO request, Long userId);
}