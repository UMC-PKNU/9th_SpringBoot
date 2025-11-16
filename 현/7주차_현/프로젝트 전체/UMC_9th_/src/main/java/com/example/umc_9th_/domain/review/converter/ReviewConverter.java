package com.example.umc_9th_.domain.review.converter;

import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.entity.ReviewImg;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // Review Entity -> MyReviewDTO (마이페이지 개별 리뷰 조회용)

    public static ReviewResponse.MyReviewDTO toMyReviewDTO(Review review) {

        // ReviewImg 엔티티 목록에서 URL만 추출
        List<String> imageUrls = review.getReviewImgs().stream()
                .map(ReviewImg::getImageUrl)
                .collect(Collectors.toList());

        return ReviewResponse.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getStoreName())
                .rating(review.getRating())
                .content(review.getContent())
                .imageUrls(imageUrls)
                .createdAt(review.getCreatedAt().toString()) // 시간 포맷은 .toString()으로 임시 처리
                .build();
    }
}