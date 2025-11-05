package com.example.umc9th.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    @GetMapping("/reviews/{mem_id}/byStoreNameAndRating")
    public List<Review> findReviewsByStoreAndRating(
            @PathVariable Long mem_id,
            @RequestParam(required = false) String store_name,
            @RequestParam(required = false) Long rating
    ){
        List<Review> result = reviewQueryService.findReviewsByStoreAndRating(mem_id,store_name, rating);
        return result;
    }
}
