package com.example.umc_9th_spring.domain.review.dto;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long reviewId,
        String storeName,
        String userName,
        int rating,
        String content,
        LocalDateTime createdAt
) {}
