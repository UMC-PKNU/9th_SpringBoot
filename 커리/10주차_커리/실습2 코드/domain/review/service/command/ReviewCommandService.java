package com.example.umc_9th_spring.domain.review.service.command;

import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewInfo createReview(Long userId, Long storeId, ReviewReqDTO.CreateReview request);
}
