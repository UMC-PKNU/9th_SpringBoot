package com.example.umc_9th_spring.domain.store.controller;

import com.example.umc_9th_spring.domain.mission.code.MissionSuccessCode;
import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.code.ReviewSuccessCode;
import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.service.command.ReviewCommandService;
import com.example.umc_9th_spring.domain.store.code.StoreSuccessCode;
import com.example.umc_9th_spring.domain.store.service.command.StoreCommandService;
import com.example.umc_9th_spring.domain.store.service.query.StoreQueryService;
import com.example.umc_9th_spring.global.annotation.ValidPage;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController implements StoreControllerDocs{

    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;

    /* 03-01 가게에 리뷰 추가하기 API  */
    @PostMapping("/{storeId}/reviews")
    @Override
    public ApiResponse<ReviewResDTO.ReviewInfo> createStoreReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,                  // 로그인 미구현이라 쿼리 파라미터로
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    ) {
        ReviewResDTO.ReviewInfo result =
                reviewCommandService.createReview(userId, storeId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, result);
    }

    /* 03-02 가게에 미션 추가하기 API  */
    @PostMapping("/{storeId}/missions")
    @Override
    public ApiResponse<MissionResDTO.MissionInfo> createStoreMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission request
    ) {
        MissionResDTO.MissionInfo result =
                storeCommandService.createMissionForStore(storeId, request);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED, result);
    }

    /* 03-03 특정 가게의 미션 목록 조회 API */
    @GetMapping("/{storeId}/missions")
    @Override
    public ApiResponse<MissionResDTO.StoreMissionPage> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                StoreSuccessCode.STORE_MISSION_LIST_FETCHED,
                storeQueryService.getStoreMissions(storeId, page)
        );

    }

}
