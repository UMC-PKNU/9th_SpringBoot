package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

// 반드시 붙여줘야 됨
@RestController
public interface ReviewControllerDocs {

    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 우디 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션을 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    // 여기는 왜 public 안 붙이지?
    ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(String storeName, Integer page);
}
