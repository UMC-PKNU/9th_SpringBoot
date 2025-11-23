package com.example.umc9th_week5.domain.review.converter;

import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.entity.Review;
import com.example.umc9th_week5.domain.store.converter.StoreConverter;
import com.example.umc9th_week5.domain.store.entity.Store;

public class ReviewConverter {
    //객체 -> DTO
    public static ReviewResDTO.ReviewInfo toReviewResDTO(Review entity){
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(entity.getId())
                .rating(entity.getRating())
                .content(entity.getContent())
                .storeInfo(StoreConverter.toStoreDTO(entity.getStore()))
                .memberInfo(MemberConverter.toMemberDTO(entity.getMember()))
                .build();
    }

    //dto -> entity
    public static Review toReviewEntity(ReviewReqDTO.reviewReqDTO dto){
        return Review.builder()
                .member(MemberConverter.toMemberEntity(dto.member()))
                .store(StoreConverter.toStoreEntity(dto.store()))
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }

    //dto -> entity
    public static Review toReviewEntity(ReviewReqDTO.reviewReqDTOForAddReview dto, Store store, Member member){
        return Review.builder()
                .member(member)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }
}
