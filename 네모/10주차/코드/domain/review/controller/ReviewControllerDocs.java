package com.example.umc9th_week5.domain.review.controller;

import com.example.umc9th_week5.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th_week5.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {
    @Operation(
            summary="리뷰 등록",
            description="사용자가 가게에 대한 리뷰를 작성합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewInfo> addReview(@RequestBody ReviewReqDTO.reviewReqDTOForAddReview dto);

    @Operation(
            summary="사용자가 쓴 리뷰 목록 조회 API",
            description="현재 로그인 된 사용자가 작성한 모든 리뷰를 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewInfoList> getReviews(@RequestParam Long memberId, @RequestParam int page);
}
