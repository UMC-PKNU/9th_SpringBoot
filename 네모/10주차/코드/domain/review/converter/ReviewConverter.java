package com.example.umc9th_week5.domain.review.converter;

import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.entity.Review;
import com.example.umc9th_week5.domain.store.converter.StoreConverter;
import com.example.umc9th_week5.domain.store.entity.Store;
import org.springframework.data.domain.Page;


public class ReviewConverter {

    //dto -> entity
    public static Review toReviewEntity(ReviewReqDTO.reviewReqDTOForAddReview dto, Store store, Member member){
        return Review.builder()
                .member(member)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .build();
    }

    //entity -> dto
    public static ReviewResDTO.ReviewInfoList toReviewInfoListDTO(Page<Review> review){
        return ReviewResDTO.ReviewInfoList.builder()
                .reviewList(review.getContent().stream()
                        .map(ReviewConverter::toReviewInfoDTO)  //review -> ReviewConverter.toReviewInfoDTO(review)
                        .toList())
                .isFirst(review.isFirst())
                .isLast(review.isLast())
                .listSize(review.getSize())
                .totalElements(review.getTotalElements())
                .totalPage(review.getTotalPages())
                .build();
    }

    public static ReviewResDTO.ReviewInfo toReviewInfoDTO(Review review){
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .nickname(review.getMember().getNickname())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
}
