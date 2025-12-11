package com.example.umc_9th_.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ReviewRequest {

    public record WriteReviewDTO(
            @NotNull Long missionId,
            @NotNull Long storeId,
            @NotNull Double rating,
            String content,
            List<String> imageUrls // 이미지 URL 목록
    ) {}
}