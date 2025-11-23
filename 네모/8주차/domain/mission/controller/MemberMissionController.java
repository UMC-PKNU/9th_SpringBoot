package com.example.umc9th_week5.domain.mission.controller;

import com.example.umc9th_week5.domain.mission.code.MemberMissionSuccessCode;
import com.example.umc9th_week5.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenges")
public class MemberMissionController {
    private final MemberMissionCommandService memberMissionCommandService;


    @PostMapping()
    public ApiResponse<MemberMissionResDTO.MemberMissionInfo> missionChallenge(@RequestBody MemberMissionReqDTO.memberMissionReqDTOForChallenge dto){
        return ApiResponse.onSuccess(MemberMissionSuccessCode.OK, memberMissionCommandService.missionChallenge(dto));
    }
}
