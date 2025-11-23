package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import com.example.umc9th.domain.store.entity.Store;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static ReviewResDto.ReviewResDefaultDto toReviewResDefaultDTO(
            Review review
    ){
        return ReviewResDto.ReviewResDefaultDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreated_at())
                // 엔티티에서 필요한 DTO를 만들어 주입
                .member(MemberDto.from(review.getMember()))
                .store(StoreDto.from(review.getStore()))
                .build();
    }

    public static ReviewResDto.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDto.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreated_at()) // Auditing으로 생성된 시간
                .build();
    }

    public static Review toReview(ReviewReqDto.JoinDto request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())   // 필드명 content
                .rating(request.getRating().longValue()) // Integer -> Long 변환
                .build();
    }
}
