package com.example.umc_9th_spring.domain.review.controller;

import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review API", description = "리뷰 관련 API 모음")
public interface ReviewControllerDocs {

    /* 02-01 특정 유저가 작성한 리뷰 조회 API */
    @Operation(
            summary = "특정 사용자 리뷰 조회",
            description = "특정 유저(userId)가 작성한 리뷰 목록을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "특정 사용자 리뷰 조회 성공 (REVIEW200_3)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "리뷰를 찾을 수 없습니다. (REVIEW404_1)"
            )
    })
    @GetMapping("/user/{userId}")
    ApiResponse<List<ReviewResDTO.ReviewInfo>> getUserReviews(
            @PathVariable Long userId
    );


    /* 02-02 특정 가게의 리뷰 조회 API */
    @Operation(
            summary = "특정 가게 리뷰 조회",
            description = "특정 가게(storeId)에 작성된 리뷰 목록을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "특정 가게 리뷰 조회 성공 (REVIEW200_4)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "리뷰를 찾을 수 없습니다. (REVIEW404_1)"
            )
    })
    @GetMapping("/store/{storeId}")
    ApiResponse<List<ReviewResDTO.ReviewInfo>> getStoreReviews(
            @PathVariable Long storeId
    );


    /* 02-03 특정 가게 리뷰 별점 필터링 */
    @Operation(
            summary = "필터링된 리뷰 목록 조회",
            description = "특정 가게(storeId)의 리뷰 중 rating 기준으로 필터링하여 조회합니다. "
                    + "로그인 미구현으로 userId는 임시 쿼리 파라미터로 사용합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "필터링된 리뷰 조회 성공 (REVIEW200_5)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "리뷰를 찾을 수 없습니다. (REVIEW404_1)"
            )
    })
    @GetMapping("/store/{storeId}/filter")
    ApiResponse<List<ReviewResDTO.ReviewInfo>> getReviewsFilter(
            @RequestParam Long userId,
            @PathVariable Long storeId,
            @RequestParam(required = false) String rating
    );
}
