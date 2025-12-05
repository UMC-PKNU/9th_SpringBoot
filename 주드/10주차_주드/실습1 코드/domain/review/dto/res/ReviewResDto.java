package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    @Builder
    public record ReviewResPreViewDTO(
            String nickname,
            Long rating,
            String content,
            LocalDate createdAt
    ){}

    @Builder
    public record ReviewResListDTO(
            List<ReviewResPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
            ){}

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
