package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.exception.code.MemberMissionSuccessCode;
import com.example.umc.domain.member.service.command.MemberMissionCommandService;
import com.example.umc.domain.mission.dto.MissionReqDto;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("member-mission")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    // 미션4. 미션 도전하기 API
    @PostMapping("/{memberId}")
    public ApiResponse<MemberMissionResDto.CreateResultDto> challengeMission(@PathVariable Long memberId,
            @RequestBody @Valid MissionReqDto.ChallengeMissionDto dto) {

        MemberMissionResDto.CreateResultDto result = memberMissionCommandService.challengeMission(memberId, dto);

        return ApiResponse.onSuccess(MemberMissionSuccessCode.MISSION_CHALLENGE_CREATED, result);
    }
}
