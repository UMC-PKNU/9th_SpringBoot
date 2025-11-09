package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.ReviewResponse;
import com.example.umc.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{memberId}")
    public List<ReviewResponse> getMyReviews(@PathVariable Long memberId,
                                             @RequestParam(required = false) String storeName,
                                             @RequestParam(required = false) Double rating) {

        List<ReviewResponse> reviews = reviewService.getReviews(memberId, storeName, rating);

        return reviews;
    }
}
