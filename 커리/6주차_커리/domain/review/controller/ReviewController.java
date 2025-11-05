package com.example.umc_9th_spring.domain.review.controller;


import com.example.umc_9th_spring.domain.review.dto.ReviewResponseDTO;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public List<ReviewResponseDTO> getReviewList(
            @RequestParam Long userId,
            @RequestParam Long storeId,
            @RequestParam String rating
    ){
        List<Review> reviews = reviewRepository.findUserReviews(userId, storeId, rating);

        // Entity → DTO 변환
        return reviews.stream()
                .map(r -> new ReviewResponseDTO(
                        r.getId(),
                        r.getStore().getName(),
                        r.getUser().getName(),
                        r.getRating(),
                        r.getContent(),
                        r.getCreatedAt()
                ))
                .toList();
    }
}
