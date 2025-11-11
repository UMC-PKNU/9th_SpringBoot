package com.example.umc_9th_spring.domain.mission.controller;

import com.example.umc_9th_spring.domain.mission.code.MissionSuccessCode;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.service.command.MissionCommandService;
import com.example.umc_9th_spring.domain.mission.service.query.MissionQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController //Controller + ResponseBody 어노테이션 합친것. JSON 으로 응답내줌.
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @GetMapping("/location/{locationId}")
    public ApiResopnse<Page<MissionResDTO.MissionInfo>> getMissionsByLocation(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<MissionResDTO.MissionInfo> missions = missionQueryService.getActiveMissionsByLocation(locationId, page, size);
        return ApiResopnse.onSuccess(MissionSuccessCode.MISSION_LIST_FETCHED, missions);
    }


}


