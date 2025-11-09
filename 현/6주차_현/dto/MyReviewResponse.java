package com.example.umc_9th_.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyReviewResponse {
    private Long reviewId;
    private String storeName;
    private Double rating;
    private String content;
    private LocalDateTime createdAt;
}
