package com.example.umc_9th_spring.domain.mission.controller;

import com.example.umc_9th_spring.domain.mission.code.MissionSuccessCode;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.mission.service.command.MissionCommandService;
import com.example.umc_9th_spring.domain.mission.service.query.MissionQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Controller + ResponseBody 어노테이션 합친것. JSON 으로 응답내줌.
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    /* 01-01 미션 목록 전부 조회 API */
    @GetMapping
    public ApiResopnse<List<MissionResDTO.MissionInfo>> getMissions(){
        List<MissionResDTO.MissionInfo> missions = missionQueryService.getMissions();
        return ApiResopnse.onSuccess(MissionSuccessCode.MISSION_LIST_FETCHED, missions);
    }

    /* 01-02 유저가 받은 미션 목록 조회 */
    @GetMapping("/me")
    public ApiResopnse<List<MissionResDTO.MissionInfo>> getMyMissions(
            @RequestParam Long userId   // 로그인 미구현으로 임시 사용
    ){
        List<MissionResDTO.MissionInfo> missions = missionQueryService.getUserMissions(userId);
        return ApiResopnse.onSuccess(MissionSuccessCode.MISSION_LIST_FETCHED, missions);
    }

    /* 01-03 유저가 특정 미션 성공 누르기 */
    @PatchMapping("/me/{missionId}")
    public ApiResopnse<MissionResDTO.MissionInfo> completeMission(
            @RequestParam Long userId,
            @PathVariable Long missionId
    ){
        MissionResDTO.MissionInfo result =
                missionCommandService.completeMission(userId, missionId);

        return ApiResopnse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, result);
    }

    /* 01-04 유저가 미션 선택(미션 받기) */
    @PostMapping("/me/{missionId}")
    public ApiResopnse<MissionResDTO.MissionInfo> receiveMission(
            @RequestParam Long userId,
            @PathVariable Long missionId
    ){
        MissionResDTO.MissionInfo result =
                missionCommandService.receiveMission(userId, missionId);

        return ApiResopnse.onSuccess(MissionSuccessCode.MISSION_RECEIVED, result);
    }



    /* 01-05 특정 지역에 속한 가게의 종료되지 않은 미션 목록 조회 API */
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


