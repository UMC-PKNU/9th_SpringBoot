package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;

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
}
