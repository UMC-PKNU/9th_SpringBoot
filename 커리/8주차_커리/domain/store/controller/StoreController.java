package com.example.umc_9th_spring.domain.store.controller;

import com.example.umc_9th_spring.domain.mission.code.MissionSuccessCode;
import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.code.ReviewSuccessCode;
import com.example.umc_9th_spring.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.service.command.ReviewCommandService;
import com.example.umc_9th_spring.domain.store.code.StoreSuccessCode;
import com.example.umc_9th_spring.domain.store.dto.req.StoreReqDTO;
import com.example.umc_9th_spring.domain.store.dto.res.StoreResDTO;
import com.example.umc_9th_spring.domain.store.service.command.StoreCommandService;
import com.example.umc_9th_spring.domain.store.service.query.StoreQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;

    /* 03-01 가게에 리뷰 추가하기 API  */
    @PostMapping("/{storeId}/reviews")
    public ApiResopnse<ReviewResDTO.ReviewInfo> createStoreReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,                  // 로그인 미구현이라 쿼리 파라미터로
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    ) {
        ReviewResDTO.ReviewInfo result =
                reviewCommandService.createReview(userId, storeId, request);
        return ApiResopnse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, result);
    }

    /* 03-02 가게에 미션 추가하기 API  */
    @PostMapping("/{storeId}/missions")
    public ApiResopnse<MissionResDTO.MissionInfo> createStoreMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission request
    ) {
        MissionResDTO.MissionInfo result =
                storeCommandService.createMissionForStore(storeId, request);
        return ApiResopnse.onSuccess(MissionSuccessCode.MISSION_CREATED, result);
    }



}
