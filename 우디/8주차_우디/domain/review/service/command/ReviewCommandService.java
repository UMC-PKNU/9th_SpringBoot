package com.example.umc.domain.review.service.command;

import com.example.umc.domain.review.dto.req.ReviewReqDto;
import com.example.umc.domain.review.dto.res.ReviewResDto;

public interface ReviewCommandService {
    // 리뷰 등록
    ReviewResDto.JoinDto createReview(Long memberId, ReviewReqDto.JoinDto dto);
}
