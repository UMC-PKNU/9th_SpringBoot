package com.example.umc9th_week5.domain.mission.controller;

import com.example.umc9th_week5.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberMissionControllerDocs {
    @Operation(
            summary="미션 도전",
            description="사용자의 미션 도전 목록에 해당 미션을 추가합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.MemberMissionInfo> missionChallenge(@RequestBody MemberMissionReqDTO.memberMissionReqDTOForChallenge dto);

    @Operation(
            summary="진행 중인 미션 조회",
            description="사용자가 현재 진행 중인 모든 미션 목록을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.MemberMissionInfoList> getInProgressMissions(@RequestParam Long memberId, @RequestParam(defaultValue = "0") int page);

    @Operation(
            summary="미션 상태 변경",
            description="사용자의 해당 미션에 대해 상태를 완료로 변경한다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.MemberMissionInfoList> completeMission(@RequestParam Long memberId, @PathVariable Long missionId, @RequestParam(defaultValue = "0") int page);


}
