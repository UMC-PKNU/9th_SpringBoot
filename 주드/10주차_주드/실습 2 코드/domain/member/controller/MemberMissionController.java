package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.converter.MemberMissionConverter;
import com.example.umc9th.domain.member.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {
    private final MemberService memberService;

    @GetMapping("/users/{userId}/missions/progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션 목록을 조회합니다. 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<MemberMissionResDTO.MyMissionListDTO> getMyProgressMission(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "page") Integer page
    ) {

        Page<MemberMission> missionPage = memberService.findMyProgressMissions(userId, page);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberMissionConverter.toMyMissionListDTO(missionPage)
        );
    }
    @GetMapping("/users/{userId}/progressMission")
    public ApiResponse<List<MemberResDTO.MyMissionDTO>> getMyProgressMission(@PathVariable(name = "userId") Long userId) {


        List<MemberMission> progressMissions = memberService.findMyProgressMissions(userId);


        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberConverter.toMyMissionDTOList(progressMissions)
        );
    }

    @GetMapping("/users/{userId}/completeMission")
    public ApiResponse<List<MemberResDTO.MyMissionDTO>> getMyCompleteMission(@PathVariable(name = "userId") Long userId) {

        List<MemberMission> completeMissions = memberService.findMyCompleteMissions(userId);


        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberConverter.toMyMissionDTOList(completeMissions)
        );
    }
    @PostMapping("/users/{userId}/missions")
    public ApiResponse<MemberMissionResDTO.CreateResultDTO> challengeMission(
            @PathVariable(name = "userId") Long userId,
            @RequestBody @Valid MemberMissionReqDTO.MissionAcceptDto request
    ) {
        MemberMission memberMission = memberService.challengeMission(request, userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, MemberConverter.toCreateResultDTO(memberMission));
    }
}
