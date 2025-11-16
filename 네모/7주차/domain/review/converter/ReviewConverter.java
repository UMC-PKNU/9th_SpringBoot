package com.example.umc9th_week5.domain.review.converter;

import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.example.umc9th_week5.domain.store.converter.StoreConverter;

public class ReviewConverter {
    //객체 -> DTO
    public static ReviewResDTO.ReviewInfo toReviewDTO(StoreReview review){
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .storeInfo(StoreConverter.toStoreDTO(review.getStore()))
                .memberInfo(MemberConverter.toMemberDTO(review.getMember()))
                .build();
    }
}
