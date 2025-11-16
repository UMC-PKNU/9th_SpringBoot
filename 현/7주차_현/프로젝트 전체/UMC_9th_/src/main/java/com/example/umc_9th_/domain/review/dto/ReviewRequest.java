package com.example.umc_9th_.domain.review.dto;

import lombok.Getter;
import java.util.List;

public class ReviewRequest {

    @Getter
    public static class WriteReviewDTO {
        private Long missionId;
        private Long storeId;
        private Double rating;
        private String content;
        private List<String> imageUrls; // 리뷰 이미지 URL 목록
    }
}