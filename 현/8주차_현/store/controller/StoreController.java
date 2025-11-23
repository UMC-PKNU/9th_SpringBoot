package com.example.umc_9th_.domain.store.controller;

import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.store.converter.StoreConverter;
import com.example.umc_9th_.domain.store.dto.StoreRequestDTO;
import com.example.umc_9th_.domain.store.dto.StoreResponseDTO;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.service.StoreCommandService;
import com.example.umc_9th_.domain.store.service.StoreService;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;          // 기존 조회용 서비스
    private final StoreCommandService storeCommandService; // 새로 만든 명령용 서비스

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponseDTO.StoreDetailDTO> getStoreDetail(@PathVariable Long storeId) {
        // 1. 서비스에서 가게 엔티티 조회
        Store store = storeService.getStoreDetail(storeId);

        // 2. 컨버터를 이용해 DTO로 변환 후 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toStoreDetailDTO(store));
    }


    // 1. 가게 추가 API
    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.JoinResultDto> joinStore(@RequestBody StoreRequestDTO.JoinDto request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toJoinResultDto(store));
    }

    // 2. 리뷰 추가 API
    @PostMapping("/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDto> createReview(@RequestBody StoreRequestDTO.ReviewDto request) {
        Review review = storeCommandService.createReview(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toCreateReviewResultDto(review));
    }

    // 3. 미션 추가 API
    @PostMapping("/missions")
    public ApiResponse<StoreResponseDTO.CreateMissionResultDto> createMission(@RequestBody StoreRequestDTO.MissionDto request) {
        Mission mission = storeCommandService.createMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toCreateMissionResultDto(mission));
    }

    // 4. 미션 도전 API
    @PostMapping("/missions/challenge")
    public ApiResponse<StoreResponseDTO.CreateUserMissionResultDto> challengeMission(@RequestBody StoreRequestDTO.ChallengeMissionDto request) {
        UserMission userMission = storeCommandService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toCreateUserMissionResultDto(userMission));
    }
}