package com.example.umc.domain.review.dto.res;

import com.example.umc.domain.review.enums.ReviewStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDto {

    @Builder
    public record JoinDto (
        Long reviewId,
        LocalDateTime createdAt
    ){}


    @Getter
    @Builder
    public static class ReviewInfo {
        private String nickname;
        private Double rating;
        private String content;
        private String storeName;
    }

}
