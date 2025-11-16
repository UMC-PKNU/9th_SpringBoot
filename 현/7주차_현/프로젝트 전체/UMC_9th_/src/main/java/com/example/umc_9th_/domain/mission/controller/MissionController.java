package com.example.umc_9th_.domain.mission.controller;

import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.dto.MissionRequest;
import com.example.umc_9th_.domain.mission.service.MissionService;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions") // /api/missions
public class MissionController {

    private final MissionService missionService;

    // 1. 미션 도전 시작 API: POST /api/missions/{missionId}/start
    @PostMapping("/{missionId}/start")
    public ApiResponse<MissionResponse.MissionStartDTO> startMission(
            @PathVariable Long missionId,
            @AuthenticationPrincipal Long userId) {

        MissionResponse.MissionStartDTO response = missionService.startMission(missionId, userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_START_SUCCESS, response);
    }

    // 2. 미션 목록 조회 API (예시: 홈 화면에서 내 지역 미션 조회)
    // 경로를 /api/home/my-missions 대신 /api/missions/home으로 잠시 배치
    @GetMapping("/home")
    public ApiResponse<MissionResponse.MissionPageDTO> getHomeMissions(
            @RequestParam String location, // 지역 필터링
            @AuthenticationPrincipal Long userId,
            @PageableDefault(size = 10) Pageable pageable) {

        MissionResponse.MissionPageDTO response = missionService.getAvailableMissions(userId, location, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
    // 3. 미션 성공 요청 API: POST /api/my-missions/{missionId}/success
    @PostMapping("/my-missions/{missionId}/success")
    public ApiResponse<MissionResponse.MissionSuccessResultDTO> requestMissionSuccess(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionRequest.SuccessMissionDTO request,
            @AuthenticationPrincipal Long userId) {

        MissionResponse.MissionSuccessResultDTO response = missionService.successMission(missionId, userId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_SUCCESS_REQUEST_SUCCESS, response);
    }
}