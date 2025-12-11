package com.example.umc.domain.review.dto.res;

import com.example.umc.domain.review.enums.ReviewStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDto {

    // 9주차 워크북
    @Builder
    public record ReviewPreViewListDto (
            List<ReviewPreViewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // 9주차 워크북
    @Builder
    public record ReviewPreViewDto (
            String ownerMemberName,
            Double score,
            String body,
            LocalDate createdAt
    ){}

    // 8주차
    @Builder
    public record JoinDto (
        Long reviewId,
        LocalDateTime createdAt
    ){}


    @Getter
    @Builder
    public static class ReviewInfo {
        private String username;
        private Double rating;
        private String content;
        private String storeName;
    }

}
