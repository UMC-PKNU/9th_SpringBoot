package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.req.ReviewReqDto;
import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc.domain.review.service.command.ReviewCommandService;
import com.example.umc.domain.review.service.query.ReviewQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 9주차 워크북
    // 가게의 리뷰 목록 조회
//    @GetMapping
//    public ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(
//            @RequestParam String storeName,
//            @RequestParam Integer page) {
//        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
//
//        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
//    }

    // 8주차 미션2. 가게에 리뷰 추가하기 API
    @PostMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResDto.JoinDto> createReview(@PathVariable Long memberId, @RequestBody ReviewReqDto.JoinDto dto) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, reviewCommandService.createReview(memberId, dto));
    }

}
