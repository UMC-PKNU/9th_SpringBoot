package com.example.umc_9th_spring.domain.review.controller;

import com.example.umc_9th_spring.domain.review.code.ReviewSuccessCode;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.service.command.ReviewCommandService;
import com.example.umc_9th_spring.domain.review.service.query.ReviewQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping
    public ApiResopnse<List<ReviewResDTO.ReviewInfo>> getReviews(
            @RequestParam Long userId,
            @RequestParam Long storeId,
            @RequestParam String rating
    ){
        List<ReviewResDTO.ReviewInfo> reviews = reviewQueryService.getReviews(userId, storeId, rating);
        return ApiResopnse.onSuccess(ReviewSuccessCode.REVIEW_LIST_FETCHED, reviews);

    }

}
