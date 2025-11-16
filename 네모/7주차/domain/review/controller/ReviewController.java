package com.example.umc9th_week5.domain.review.controller;

import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.domain.review.service.query.ReviewQueryService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("search")
    public ApiResponse<List<ReviewResDTO.ReviewInfo>> searchReview(@RequestParam Long userId,
                                                                   @RequestParam(required=false) String storeName,
                                                                   @RequestParam(required=false) Float rating){

        return reviewQueryService.searchReview(userId, storeName, rating);
    }
}
