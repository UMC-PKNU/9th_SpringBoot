package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {
    private final MemberService memberService;

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
