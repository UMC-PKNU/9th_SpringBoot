package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionController {
    private final MissionQueryService missionQueryService;

    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회합니다. query string으로 page 번호를 주세요 (1번부터 시작).")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionResListDTO> getMissionsByStore(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ) {
        // 서비스 호출
        Page<Mission> missionPage = missionQueryService.getMissionsByStore(storeId, page);

        // 컨버터 호출
        MissionResDTO.MissionResListDTO response = MissionConverter.toMissionResListDTO(missionPage);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
