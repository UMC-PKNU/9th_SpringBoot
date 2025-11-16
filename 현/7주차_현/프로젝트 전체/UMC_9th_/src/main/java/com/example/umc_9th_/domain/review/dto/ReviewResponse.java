package com.example.umc_9th_.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {

    @Getter
    @Builder
    public static class ReviewIdDTO {
        // 리뷰 등록 성공 시 반환할 데이터
        private Long reviewId;
    }

    @Getter
    @Builder
    public static class MyReviewDTO {
        // 개별 리뷰 응답 DTO
        private Long reviewId;
        private String storeName;
        private Double rating;
        private String content;
        private List<String> imageUrls; // 이미지 목록 추가
        private String createdAt;
    }

    @Getter
    @Builder
    public static class MyReviewPageDTO {
        // 마이페이지 리뷰 목록 (페이징 정보 포함)
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
        private Integer pageSize;
        private List<MyReviewDTO> reviewList;
    }
}