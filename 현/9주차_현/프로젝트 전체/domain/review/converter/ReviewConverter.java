package com.example.umc_9th_.domain.review.converter;

import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.entity.ReviewImg;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponse.ReviewIdDTO toReviewIdDTO(Review review) {
        return new ReviewResponse.ReviewIdDTO(
                review.getId(),
                review.getCreatedAt()
        );
    }

    // Page<Review> -> MyReviewPageDTO 변환
    public static ReviewResponse.MyReviewPageDTO toMyReviewPageDTO(Page<Review> reviewPage) {
        List<ReviewResponse.MyReviewDTO> reviewList = reviewPage.stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return new ReviewResponse.MyReviewPageDTO(
                reviewList,
                reviewPage.getTotalElements(),
                reviewPage.getTotalPages(),
                reviewPage.isFirst(),
                reviewPage.isLast(),
                reviewPage.getNumber() + 1
        );
    }

    // Review -> MyReviewDTO 변환
    public static ReviewResponse.MyReviewDTO toMyReviewDTO(Review review) {
        List<String> imageUrls = review.getReviewImgs().stream()
                .map(ReviewImg::getImageUrl)
                .collect(Collectors.toList());

        return new ReviewResponse.MyReviewDTO(
                review.getId(),
                review.getStore().getStoreName(),
                review.getRating(),
                review.getContent(),
                imageUrls,
                review.getCreatedAt()
        );
    }
}