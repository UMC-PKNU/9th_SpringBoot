package com.example.umc9th_week5.domain.mission.controller;

import com.example.umc9th_week5.domain.mission.code.MissionSuccessCode;
import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.domain.mission.service.command.MissionCommandService;
import com.example.umc9th_week5.domain.mission.service.query.MissionQueryService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionController implements MissionControllerDocs{
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/missions")
    public ApiResponse<MissionResDTO.MissionInfo> addMission(@RequestBody MissionReqDTO.missionReqDTO dto){
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionCommandService.addMission(dto));
    }

    @Override
    @GetMapping("{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionInfoList> getMissionByStore(@PathVariable Long storeId, @RequestParam(defaultValue = "0") int page){
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionQueryService.getMissionByStore(storeId, page));
    }
}
