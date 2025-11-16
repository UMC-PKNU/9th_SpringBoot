package com.example.umc_9th_.domain.review.controller;

import com.example.umc_9th_.domain.review.dto.ReviewRequest;
import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.service.ReviewService;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 1. 리뷰 등록 API: POST /api/reviews
    @PostMapping("/write")
    public ApiResponse<ReviewResponse.ReviewIdDTO> writeReview(
            @RequestBody ReviewRequest.WriteReviewDTO request,
            @AuthenticationPrincipal Long userId) {
        ReviewResponse.ReviewIdDTO result = reviewService.writeReview(request, 1L);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}