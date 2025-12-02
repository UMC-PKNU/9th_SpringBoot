package com.example.umc_9th_spring.domain.mission.controller;

import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mission API", description = "미션 관련 API 모음")
public interface MissionControllerDocs {

    /* 01-01 미션 목록 전부 조회 API */
    @Operation(
            summary = "전체 미션 목록 조회",
            description = "시스템에 등록된 전체 미션 목록을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 목록 조회 성공 (MISSION200_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 에러"
            )
    })
    @GetMapping
    ApiResponse<List<MissionResDTO.MissionInfo>> getMissions();


    /* 01-02 유저가 받은 미션 목록 조회 */
    @Operation(
            summary = "유저가 받은 미션 목록 조회",
            description = "특정 유저가 받은(수령한) 미션 목록을 조회합니다. 로그인 미구현으로 userId를 쿼리 파라미터로 받습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 목록 조회 성공 (MISSION200_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "유저가 존재하지 않음"
            )
    })
    @GetMapping("/me")
    ApiResponse<List<MissionResDTO.MissionInfo>> getMyMissions(
            @RequestParam Long userId   // 로그인 미구현으로 임시 사용
    );


    /* 01-03 유저가 특정 미션 성공 누르기 */
    @Operation(
            summary = "미션 완료 처리",
            description = "유저가 특정 미션을 완료했을 때 해당 미션을 완료 상태로 변경합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 완료 성공 (MISSION200_2)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "유저가 해당 미션을 받지 않았거나 이미 완료된 미션입니다. " +
                            "(MISSION400_1, MISSION400_2)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 미션입니다. (MISSION404_1)"
            )
    })
    @PatchMapping("/me/{missionId}")
    ApiResponse<MissionResDTO.MissionInfo> completeMission(
            @RequestParam Long userId,
            @PathVariable Long missionId
    );


    /* 01-04 유저가 미션 선택(미션 받기) */
    @Operation(
            summary = "미션 받기",
            description = "유저가 특정 미션을 선택하여 수령합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "미션 받기 성공 (MISSION201_2)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "이미 받은 미션입니다. (MISSION400_3)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 미션입니다. (MISSION404_1)"
            )
    })
    @PostMapping("/me/{missionId}")
    ApiResponse<MissionResDTO.MissionInfo> receiveMission(
            @RequestParam Long userId,
            @PathVariable Long missionId
    );


    /* 01-05 특정 지역에 속한 가게의 종료되지 않은 미션 목록 조회 API */
    @Operation(
            summary = "특정 지역의 진행 중인 미션 목록 조회",
            description = "특정 지역(locationId)에 속한 가게들의 '종료되지 않은' 미션 목록을 페이징하여 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 목록 조회 성공 (MISSION200_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "해당 지역이 존재하지 않음"
            )
    })
    @GetMapping("/location/{locationId}")
    ApiResponse<Page<MissionResDTO.MissionInfo>> getMissionsByLocation(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );
}
