package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResDto {
    @Builder
    @Getter
    public static class ReviewResDefaultDto{
        private final Long id;
        private final MemberDto member;
        private final String content;
        private final Long rating;
        private final LocalDateTime createdAt;
        private final StoreDto store;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }
}
