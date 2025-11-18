package com.example.umc_9th_spring.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    @Builder
    public static class CreateReview {
        private Long userId;
        private Long storeId;
        private String content;
        private int rating;
    }

    @Getter
    @Builder
    public static class UpdateReview {
        private Long reviewId;
        private String content;
        private int rating;
    }
}
