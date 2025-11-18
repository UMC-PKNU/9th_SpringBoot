package com.example.umc_9th_spring.domain.review.controller;

import com.example.umc_9th_spring.domain.review.code.ReviewSuccessCode;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.service.command.ReviewCommandService;
import com.example.umc_9th_spring.domain.review.service.query.ReviewQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    /* 02-01 특정 유저가 작성한 리뷰 조회 API*/
    @GetMapping("/user/{userId}")
    public ApiResopnse<List<ReviewResDTO.ReviewInfo>> getUserReviews(
        @PathVariable Long userId
    ){
        List<ReviewResDTO.ReviewInfo> reviews = reviewQueryService.getUserReviews(userId);
        return ApiResopnse.onSuccess(ReviewSuccessCode.REVIEW_LIST_ME_FETCHED, reviews);
    }

    /* 02-02 특정 가게 리뷰 조회 API*/
    @GetMapping("/store/{storeId}")
    public ApiResopnse<List<ReviewResDTO.ReviewInfo>> getStoreReviews(
        @PathVariable Long storeId
    ){
        List<ReviewResDTO.ReviewInfo> reviews = reviewQueryService.getStoreReviews(storeId);
        return ApiResopnse.onSuccess(ReviewSuccessCode.REVIEW_LIST_STORE_FETCHED, reviews);
    }

    /* 02-03 특정 가게 리뷰 별점순으로 필터링된 리뷰 목록 조회 API*/
    // 로그인이 구현안되어잇어서 API 명세서에는 userId 쿼리 파라미터 기입 안했음. (나중에 구현하면 @AuthenticationPrincipal 이걸로 현재 로그인된 사용자 id 들고올 수 있음)
    @GetMapping("/store/{storeId}")
    public ApiResopnse<List<ReviewResDTO.ReviewInfo>> getReviewsFilter(
            @RequestParam Long userId,
            @PathVariable Long storeId,
            @RequestParam(required = false) String rating
    ){
        List<ReviewResDTO.ReviewInfo> reviews = reviewQueryService.getReviewsFilter(userId, storeId, rating);
        return ApiResopnse.onSuccess(ReviewSuccessCode.REVIEW_FITERED_LIST_FETCHED, reviews);

    }




}
