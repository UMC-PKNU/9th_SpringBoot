package com.example.umc9th_week5.domain.review.controller;

import com.example.umc9th_week5.domain.review.code.ReviewSuccessCode;
import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.service.command.ReviewCommandService;
import com.example.umc9th_week5.domain.review.service.query.ReviewQueryService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/review/search")
    public ApiResponse<List<ReviewResDTO.ReviewInfo>> searchReview(@RequestParam Long memberId,
                                                                   @RequestParam(required=false) String storeName,
                                                                   @RequestParam(required=false) Float rating){
        return reviewQueryService.searchReview(memberId, storeName, rating);
    }

    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewInfo> addReview(@RequestBody ReviewReqDTO.reviewReqDTOForAddReview dto){
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewCommandService.addReview(dto));

    }
}
