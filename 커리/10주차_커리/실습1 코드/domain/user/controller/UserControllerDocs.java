package com.example.umc_9th_spring.domain.user.controller;

import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.global.annotation.ExistUsers;
import com.example.umc_9th_spring.global.annotation.ValidPage;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "유저 관련 API 모음")
public interface UserControllerDocs {

    /* 00-01 회원가입 API */
    @Operation(
            summary = "회원가입",
            description = "사용자가 이메일과 비밀번호 등을 입력하여 회원가입을 진행합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "회원가입 성공 (USER201_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "이미 사용 중인 이메일입니다. (USER409_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 데이터입니다. (USER400_2 - 잘못된 요청)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "내부 서버 오류 (USER500_1)"
            )
    })
    @PostMapping
    ApiResponse<UserResDTO.UserInfo> userSignUp(
            @RequestBody UserReqDTO.SignUp request
    );


    /* 00-02 홈 화면 조회 API */
    @Operation(
            summary = "유저 정보 조회",
            description = "유저의 홈 정보(기본 유저 정보)를 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "유저 정보 조회 성공 (USER200_2)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "해당 유저를 찾을 수 없습니다. (USER404_1)"
            )
    })
    @GetMapping("/{userId}")
    ApiResponse<UserResDTO.UserInfo> getUserInfo(
            @PathVariable Long userId
    );


    /* 00-03 유저의 미션 상태별 조회 API (페이징 지원) */
    @Operation(
            summary = "유저 미션 상태별 조회",
            description = "유저가 보유한 미션을 상태(status)에 따라 페이징하여 조회합니다. "
                    + "예: ACTIVE, COMPLETED, EXPIRED 등"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "유저 미션 목록 조회 성공 (USER200_3)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 미션 상태(status)입니다. (USER400_2)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "유저를 찾을 수 없습니다. (USER404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류 (USER500_1)"
            )
    })
    @GetMapping("/{userId}/userMissionMap")
    ApiResponse<Page<MissionResDTO.MissionInfo>> getUserMissionsByStatus(
            @PathVariable Long userId,
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    /* 00-04 내가 작성한 리뷰 목록 조회 API */
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "유저가 작성한 모든 리뷰를 최신순으로 페이징하여 조회합니다. "
                    + "한 페이지는 10개이며, page는 1 이상의 값을 전달해야 합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 목록 조회 성공 (USER200_4)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "유저를 찾을 수 없습니다. (USER404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 page 값입니다. (PAGE400_1)"
            )
    })
    @GetMapping("/{userId}/reviews")
    ApiResponse<ReviewResDTO.MyReviewPage> getMyReviews(
            @ExistUsers @PathVariable Long userId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    );


    /* 00-05 내가 진행중인 미션 목록 조회 API */
    @Operation(
            summary = "진행 중인 미션 목록 조회",
            description = "유저가 수락한 미션 중, 현재 진행 중(in_progress) 상태의 미션을 페이징하여 조회합니다. "
                    + "한 페이지는 10개이며 page는 1 이상의 값을 전달해야 합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "진행중인 미션 목록 조회 성공 (USER200_5)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "유저를 찾을 수 없습니다. (USER404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "잘못된 page 값입니다. (PAGE400_1)"
            )
    })
    @GetMapping("/{userId}/missions/in-progress")
    ApiResponse<MissionResDTO.UserMissionPage> getInProgressMissions(
            @ExistUsers @PathVariable Long userId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    );


    /* 00-06 진행중인 미션 완료 처리 API */
    @Operation(
            summary = "진행중인 미션 완료 처리",
            description = "유저가 진행 중인(in_progress) 미션을 완료 상태(completed)로 변경합니다. "
                    + "변경된 미션 정보를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 완료 성공 (USER200_6)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "유저 또는 미션을 찾을 수 없습니다. (USER404_1 / MISSION404_1)"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "이미 완료된 미션입니다. (MISSION409_1)"
            )
    })
    @PatchMapping("/{userId}/missions/{userMissionId}")
    ApiResponse<MissionResDTO.UserMissionInfo> completeMission(
            @ExistUsers @PathVariable Long userId,
            @PathVariable Long userMissionId
    );

}
