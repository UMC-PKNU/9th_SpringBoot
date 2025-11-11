package com.example.umc_9th_spring.domain.review.converter;

import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.domain.user.entity.User;

public class ReviewConverter {

    public static ReviewResDTO.ReviewInfo toReviewInfoDTO(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .id(review.getId())
                .userName(review.getUser().getName())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewSummary toReviewSummaryDTO(Review review) {
        return ReviewResDTO.ReviewSummary.builder()
                .id(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .build();
    }

    //요청 DTO를 바로 엔티티화 하기 위함.
    public static Review toEntity(ReviewReqDTO.CreateReview dto, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();
    }


}
