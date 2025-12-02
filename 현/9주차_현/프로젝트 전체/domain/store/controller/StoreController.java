package com.example.umc_9th_.domain.store.controller;

import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.store.converter.StoreConverter;
import com.example.umc_9th_.domain.store.dto.StoreRequest;
import com.example.umc_9th_.domain.store.dto.StoreResponse;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.global.annotation.CheckPage;
import com.example.umc_9th_.domain.store.exception.code.StoreSuccessCode;
import com.example.umc_9th_.domain.store.service.command.StoreCommandService;
import com.example.umc_9th_.domain.store.service.query.StoreQueryService;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/stores")
@Tag(name = "가게 관련 API", description = "가게 등록, 조회, 리뷰 및 미션 관련 API") // ✅ Controller 설명 추가
public class StoreController {

    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    // 0. 가게 상세 조회 API
    @GetMapping("/{storeId}")
    @Operation(summary = "가게 상세 조회 API", description = "가게 ID를 통해 가게의 상세 정보를 조회합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID")
    })
    public ApiResponse<StoreResponse.StoreDetailDTO> getStoreDetail(@PathVariable Long storeId) {
        Store store = storeQueryService.getStoreDetail(storeId);
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_CHECK, StoreConverter.toStoreDetailDTO(store));
    }

    // 1. 가게 추가 API
    @PostMapping("/")
    @Operation(summary = "가게 등록 API", description = "새로운 가게를 등록합니다.")
    public ApiResponse<StoreResponse.JoinResultDTO> joinStore(@RequestBody StoreRequest.JoinDTO request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_REGISTER, StoreConverter.toJoinResultDTO(store));
    }

    // 2. 리뷰 추가 API
    @PostMapping("/reviews")
    @Operation(summary = "리뷰 등록 API", description = "가게에 새로운 리뷰를 등록합니다.")
    public ApiResponse<StoreResponse.CreateReviewResultDTO> createReview(@RequestBody StoreRequest.ReviewDTO request) {
        Review review = storeCommandService.createReview(request);
        return ApiResponse.onSuccess(StoreSuccessCode.REVIEW_REGISTER, StoreConverter.toCreateReviewResultDTO(review));
    }

    // 3. 미션 추가 API
    @PostMapping("/missions")
    @Operation(summary = "미션 등록 API", description = "가게에 새로운 미션을 등록합니다.")
    public ApiResponse<StoreResponse.CreateMissionResultDTO> createMission(@RequestBody StoreRequest.MissionDTO request) {
        Mission mission = storeCommandService.createMission(request);
        return ApiResponse.onSuccess(StoreSuccessCode.MISSION_REGISTER, StoreConverter.toCreateMissionResultDTO(mission));
    }

    // 4. 미션 도전 API
    @PostMapping("/missions/challenge")
    @Operation(summary = "미션 도전 API", description = "특정 미션에 도전합니다.")
    public ApiResponse<StoreResponse.CreateUserMissionResultDTO> challengeMission(@RequestBody StoreRequest.ChallengeMissionDTO request) {
        UserMission userMission = storeCommandService.challengeMission(request);
        return ApiResponse.onSuccess(StoreSuccessCode.MISSION_CHALLENGE, StoreConverter.toCreateUserMissionResultDTO(userMission));
    }

    // [API 2] 특정 가게의 미션 목록 (페이징 + 커스텀 검증)
    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "가게 ID를 통해 해당 가게의 미션 목록을 조회합니다. (페이징 포함)")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<StoreResponse.MissionListDTO> getMissionsByStore(
            @PathVariable Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        // 프론트는 1부터 주지만, 스프링 Pageable은 0부터 시작하므로 -1 처리
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Mission> missionPage = storeQueryService.getMissionsByStore(storeId, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toMissionListDTO(missionPage));
    }

    // [API 4] 미션 완료 처리 API (PATCH)
    @PatchMapping("/missions/{userMissionId}/complete")
    @Operation(summary = "미션 완료 처리 API", description = "진행 중인 미션을 완료 상태로 변경합니다.")
    @Parameters({
            @Parameter(name = "userMissionId", description = "진행 중인 유저 미션의 ID")
    })
    public ApiResponse<StoreResponse.CreateUserMissionResultDTO> completeMission(
            @PathVariable Long userMissionId
    ) {
        UserMission userMission = storeCommandService.completeMission(userMissionId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toCreateUserMissionResultDTO(userMission));
    }
}