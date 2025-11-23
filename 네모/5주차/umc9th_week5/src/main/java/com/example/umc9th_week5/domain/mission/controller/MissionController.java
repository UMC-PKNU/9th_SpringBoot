package com.example.umc9th_week5.domain.mission.controller;

import com.example.umc9th_week5.domain.mission.code.MissionSuccessCode;
import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/missions")
    public ApiResponse<MissionResDTO.MissionInfo> addMission(@RequestBody MissionReqDTO.missionReqDTO dto){
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionCommandService.addMission(dto));
    }
}
