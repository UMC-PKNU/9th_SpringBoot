package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDto;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;

//public interface ReviewControllerDocs {
//    @Operation(
//            summary = "(내 리뷰 조회 API By 주드)",
//            description = "내가 쓴 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
//    )
//    @ApiResponses({
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
//    })
//    ApiResponse<ReviewResDto.ReviewResListDTO> getMyReviews();
//}
