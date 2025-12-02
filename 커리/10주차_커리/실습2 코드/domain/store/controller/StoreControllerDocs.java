package com.example.umc_9th_spring.domain.store.controller;

import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.global.annotation.ValidPage;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(name = "Store API", description = "가게 관련 API 모음")
public interface StoreControllerDocs {

    /* 03-01 가게에 리뷰 추가하기 API  */
    @Operation(
            summary = "가게 리뷰 등록",
            description = "특정 가게(storeId)에 대해 사용자가 리뷰를 등록합니다. "
                    + "로그인 미완성으로 userId는 쿼리 파라미터로 입력합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "리뷰 등록 성공 (REVIEW201_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "해당 가게를 찾을 수 없습니다. (STORE404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "리뷰 저장 실패 (REVIEW500_1)"
            )
    })
    @PostMapping("/{storeId}/reviews")
    ApiResponse<ReviewResDTO.ReviewInfo> createStoreReview(
            @PathVariable Long storeId,
            @RequestParam Long userId, // 로그인 미구현이라 쿼리 파라미터로
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    );


    /* 03-02 가게에 미션 추가하기 API  */
    @Operation(
            summary = "가게에 미션 추가",
            description = "특정 가게(storeId)에 관리자 권한으로 신규 미션을 등록합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "미션 생성 성공 (MISSION201_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "해당 가게를 찾을 수 없습니다. (STORE404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 에러로 미션 생성 실패"
            )
    })
    @PostMapping("/{storeId}/missions")
    ApiResponse<MissionResDTO.MissionInfo> createStoreMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission request
    );

    /* 03-03 특정 가게의 미션 목록 조회 API */
    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "특정 가게(storeId)에 등록된 미션을 페이징하여 조회합니다. "
                    + "한 페이지는 10개이며 page는 1 이상의 값이어야 합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "가게 미션 목록 조회 성공 (STORE200_3)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "해당 가게를 찾을 수 없습니다. (STORE404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 page 값입니다. (PAGE400_1)"
            )
    })
    @GetMapping("/{storeId}/missions")
    ApiResponse<MissionResDTO.StoreMissionPage> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    );

}
