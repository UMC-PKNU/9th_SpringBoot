package com.example.umc_9th_spring.domain.review.converter;

import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

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

    public static ReviewResDTO.MyReviewInfo toMyReviewInfo(Review review) {

        return ReviewResDTO.MyReviewInfo.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewPage toMyReviewPage(Page<Review> page) {

        List<ReviewResDTO.MyReviewInfo> contents = page.getContent()
                .stream()
                .map(ReviewConverter::toMyReviewInfo)
                .toList();

        return ReviewResDTO.MyReviewPage.builder()
                .reviews(contents)
                .page(page.getNumber() + 1)           // 프론트로 돌려줄 때 0-based → 1-based
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }


}
