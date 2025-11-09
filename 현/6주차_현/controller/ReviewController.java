package com.example.umc_9th_.domain.review.controller;

import com.example.umc_9th_.domain.review.dto.MyReviewResponse;
import com.example.umc_9th_.domain.review.service.ReviewService;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    @PostMapping("/write")
    public String writeReview() {
        reviewService.writeReview(
                2060L,
                42L,
                55L,
                "음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무나도 행복한 식사였답니다. 다음에 또 올게요!",
                5
        );
        return "리뷰가 성공적으로 등록되었습니다.";
    }
    @GetMapping
    public Page<MyReviewResponse> getMyReviews(
            @RequestParam Long userId, // 임시로 userId 직접 받기
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingRange,
            @PageableDefault(size = 10) Pageable pageable) {

        return reviewService.getMyReviews(userId, storeName, ratingRange, pageable);
    }
    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
