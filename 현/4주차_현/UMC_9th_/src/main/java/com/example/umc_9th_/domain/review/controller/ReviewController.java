package com.example.umc_9th_.domain.review.controller;

import com.example.umc_9th_.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

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
}
