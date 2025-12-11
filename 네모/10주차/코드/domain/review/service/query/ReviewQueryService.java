package com.example.umc9th_week5.domain.review.service.query;

import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;

import java.util.List;

public interface ReviewQueryService {
    ApiResponse<List<ReviewResDTO.ReviewInfo>> searchReview(Long memberId, String storeName, Float rating);
    ReviewResDTO.ReviewInfoList getReview(Long memberId, int page);
}
