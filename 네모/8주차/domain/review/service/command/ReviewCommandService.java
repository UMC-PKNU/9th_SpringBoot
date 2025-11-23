package com.example.umc9th_week5.domain.review.service.command;

import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewInfo addReview(ReviewReqDTO.reviewReqDTOForAddReview dto);
}
