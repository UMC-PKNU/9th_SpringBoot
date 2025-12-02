package com.example.umc_9th_.domain.review.controller;

import com.example.umc_9th_.domain.review.converter.ReviewConverter;
import com.example.umc_9th_.domain.review.dto.ReviewRequest;
import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_9th_.domain.review.service.command.ReviewCommandService;
import com.example.umc_9th_.domain.review.service.query.ReviewQueryService;
import com.example.umc_9th_.global.annotation.CheckPage; // 커스텀 어노테이션
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated // @CheckPage 사용 위해 필수
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 1. 리뷰 등록 API
    @PostMapping("/write")
    @Operation(summary = "리뷰 등록 API", description = "가게에 리뷰를 등록합니다.")
    public ApiResponse<ReviewResponse.ReviewIdDTO> writeReview(
            @RequestBody @Valid ReviewRequest.WriteReviewDTO request,
            @AuthenticationPrincipal Long userId) {

        Review review = reviewCommandService.writeReview(request, 1L); // userId 임시 하드코딩
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_REGISTER, ReviewConverter.toReviewIdDTO(review));
    }

    // 2. 마이페이지 리뷰 목록 조회 API
    @GetMapping("/me")
    @Operation(summary = "내가 쓴 리뷰 목록 조회 API", description = "마이페이지에서 내가 작성한 리뷰 목록을 조회합니다. (페이징 포함)")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<ReviewResponse.MyReviewPageDTO> getMyReviews(
            @AuthenticationPrincipal Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingRange,
            @CheckPage @RequestParam(name = "page") Integer page // 커스텀 어노테이션 적용
    ) {
        // page - 1 처리
        Pageable pageable = PageRequest.of(page - 1, 10);

        // 1. 엔티티 페이지 조회
        Page<Review> reviewPage = reviewQueryService.getMyReviews(1L, storeName, ratingRange, pageable);

        // 2. DTO로 변환
        return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_LIST, ReviewConverter.toMyReviewPageDTO(reviewPage));
    }
}