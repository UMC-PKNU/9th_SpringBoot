package com.example.umc9th_week5.domain.mission.controller;

import com.example.umc9th_week5.domain.mission.code.MemberMissionSuccessCode;
import com.example.umc9th_week5.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th_week5.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenges")
public class MemberMissionController implements MemberMissionControllerDocs{
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping()
    public ApiResponse<MemberMissionResDTO.MemberMissionInfo> missionChallenge(@RequestBody MemberMissionReqDTO.memberMissionReqDTOForChallenge dto){
        return ApiResponse.onSuccess(MemberMissionSuccessCode.OK, memberMissionCommandService.missionChallenge(dto));
    }

    @Override
    @GetMapping()
    public ApiResponse<MemberMissionResDTO.MemberMissionInfoList> getInProgressMissions(@RequestParam Long memberId, @RequestParam(defaultValue = "0") int page){
        return ApiResponse.onSuccess(MemberMissionSuccessCode.OK, memberMissionQueryService.getInProgressMissions(memberId, page));
    }

    @Override
    @PatchMapping("/{missionId}")
    public ApiResponse<MemberMissionResDTO.MemberMissionInfoList> completeMission(@RequestParam Long memberId, @PathVariable Long missionId, @RequestParam(defaultValue = "0") int page) {
        return ApiResponse.onSuccess(MemberMissionSuccessCode.OK, memberMissionCommandService.completeMission(memberId, missionId, page));
    }
}
