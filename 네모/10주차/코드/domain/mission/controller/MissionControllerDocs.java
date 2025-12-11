package com.example.umc9th_week5.domain.mission.controller;

import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {
    @Operation(
            summary="미션 추가",
            description="가게에 미션을 추가합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionInfo> addMission(@RequestBody MissionReqDTO.missionReqDTO dto);

    @Operation(
            summary="가게별 미션 조회",
            description="선택한 가게에 따른 모든 미션을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionInfoList> getMissionByStore(@PathVariable Long storeId, @RequestParam int page);

}
