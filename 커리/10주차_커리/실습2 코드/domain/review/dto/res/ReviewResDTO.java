package com.example.umc_9th_spring.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class ReviewInfo {
        private Long id;
        private String userName;
        private String storeName;
        private String content;
        private int rating;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class ReviewSummary {
        private Long id;
        private String storeName;
        private int rating;
    }

    @Getter
    @Builder
    public static class MyReviewInfo {
        private Long reviewId;
        private String storeName;
        private String content;
        private int rating;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class MyReviewPage {
        private List<MyReviewInfo> reviews;
        private int page;              // 1-based
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean first;
        private boolean last;
    }

}
