package com.example.umc.domain.review.converter;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.review.dto.req.ReviewReqDto;
import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.enums.ReviewStatus;
import com.example.umc.domain.store.entity.Store;

public class ReviewConverter {

    // 리뷰 생성 (DTO + Member + Store -> Entity)
    public static Review toReview(ReviewReqDto.JoinDto dto, Member member, Store store) {
        return Review.builder()
                .store(store)
                .member(member)
                .rating(dto.rating())
                .content(dto.content())
                .imageUrl(dto.imageUrl())
                .status(ReviewStatus.ACTIVE) // 기본 상태 설정
                .build();
    }

    // Entity -> DTO
    public static ReviewResDto.JoinDto toJoinDto(Review review) {
        return ReviewResDto.JoinDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 응답 DTO 생성 (Entity -> DTO)
    public static ReviewResDto.JoinDto toCreateResultDto(Review review) {
        return ReviewResDto.JoinDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }





    public static ReviewResDto.ReviewInfo GetReviewInfoList(Review review, Member member, Store store) {
        return ReviewResDto.ReviewInfo.builder()
                .nickname(member.getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .storeName(store.getName())
                .build();
    }
}
