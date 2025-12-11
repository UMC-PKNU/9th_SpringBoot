package com.example.umc_9th_.domain.mission.controller;

import com.example.umc_9th_.domain.mission.dto.MissionRequest;
import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_9th_.domain.mission.service.command.MissionCommandService;
import com.example.umc_9th_.domain.mission.service.query.MissionQueryService;
import com.example.umc_9th_.global.annotation.CheckPage; // 커스텀 어노테이션
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    // 1. 미션 도전 시작
    @PostMapping("/{missionId}/start")
    @Operation(summary = "미션 도전 API", description = "미션을 도전 상태로 변경합니다.")
    public ApiResponse<MissionResponse.MissionStartDTO> startMission(
            @PathVariable Long missionId,
            @AuthenticationPrincipal Long userId) {

        MissionResponse.MissionStartDTO response = missionCommandService.startMission(missionId, userId);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_START, response);
    }

    // 2. 홈 화면 미션 조회 (페이징 + 커스텀 검증)
    @GetMapping("/home")
    @Operation(summary = "홈 화면 미션 조회 API", description = "내 지역의 도전 가능한 미션을 조회합니다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<MissionResponse.MissionPageDTO> getHomeMissions(
            @RequestParam String location,
            @AuthenticationPrincipal Long userId,
            @CheckPage @RequestParam(name = "page") Integer page // ✅ 커스텀 어노테이션
    ) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        MissionResponse.MissionPageDTO response = missionQueryService.getAvailableMissions(userId, location, pageable);

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_FETCH, response);
    }

    // 3. 미션 성공 인증
    @PostMapping("/my-missions/{missionId}/success")
    @Operation(summary = "미션 성공 인증 API", description = "인증 코드를 통해 미션을 성공 처리합니다.")
    public ApiResponse<MissionResponse.MissionSuccessResultDTO> requestMissionSuccess(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionRequest.SuccessMissionDTO request,
            @AuthenticationPrincipal Long userId) {

        MissionResponse.MissionSuccessResultDTO response = missionCommandService.successMission(missionId, userId, request);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_SUCCESS, response);
    }
}