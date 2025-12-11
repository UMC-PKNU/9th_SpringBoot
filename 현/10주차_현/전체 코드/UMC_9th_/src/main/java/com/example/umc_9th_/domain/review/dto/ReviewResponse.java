package com.example.umc_9th_.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {

    public record ReviewIdDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    // 마이페이지 리뷰 목록 (페이징 포함)
    public record MyReviewPageDTO(
            List<MyReviewDTO> reviewList,
            Long totalElements,
            Integer totalPage,
            Boolean isFirst,
            Boolean isLast,
            Integer currentPage
    ) {}

    // 개별 리뷰 정보
    public record MyReviewDTO(
            Long reviewId,
            String storeName,
            Double rating,
            String content,
            List<String> imageUrls,
            LocalDateTime createdAt
    ) {}
}