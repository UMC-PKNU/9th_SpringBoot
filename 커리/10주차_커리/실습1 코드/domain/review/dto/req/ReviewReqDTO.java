package com.example.umc_9th_spring.domain.review.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    @Builder
    public static class CreateReview {
        private Long userId;
        private Long storeId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;

        @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 최대 5점입니다.")
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
