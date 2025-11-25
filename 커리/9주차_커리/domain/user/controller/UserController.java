package com.example.umc_9th_spring.domain.user.controller;


import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.user.code.UserSuccessCode;
import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.service.command.UserCommandService;
import com.example.umc_9th_spring.domain.user.service.query.UserQueryService;
import com.example.umc_9th_spring.global.annotation.ExistUsers;
import com.example.umc_9th_spring.global.annotation.ValidPage;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerDocs{

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;


    /* 00-01 회원가입 API */
    @PostMapping
    @Override
    public ApiResponse<UserResDTO.UserInfo> userSignUp(
            @RequestBody UserReqDTO.SignUp request
    ){
        UserResDTO.UserInfo response = userCommandService.userSignUp(request);
        return ApiResponse.onSuccess(UserSuccessCode.USER_SIGNUP_CREATED, response);
    }

    /* 00-02 홈 화면 조회 API */
    @GetMapping("/{userId}")
    @Override
    public ApiResponse<UserResDTO.UserInfo> getUserInfo(
            @PathVariable Long userId
    ){
        UserResDTO.UserInfo userInfo = userQueryService.getUserInfo(userId);
        return ApiResponse.onSuccess(UserSuccessCode.USER_FETCH_SUCCESS, userInfo);
    }

    /* 00-03 유저의 미션 상태별 조회 API (페이징 지원) */
    @GetMapping("/{userId}/userMissionMap")
    @Override
    public ApiResponse<Page<MissionResDTO.MissionInfo>> getUserMissionsByStatus(
            @PathVariable Long userId,
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MissionResDTO.MissionInfo> missions =
                userQueryService.getUserMissionsByStatus(userId, status, page, size);
        return ApiResponse.onSuccess(UserSuccessCode.USER_MISSION_LIST_FETCHED, missions);
    }

    /* 00-04 내가 작성한 리뷰 목록 조회 API */
    @GetMapping("/{userId}/reviews")
    @Override
    public ApiResponse<ReviewResDTO.MyReviewPage> getMyReviews(
            @ExistUsers @PathVariable Long userId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.USER_MY_REVIEW_LIST_FETCHED ,userQueryService.getMyReviews(userId, page));
    }

    /* 00-05 내가 진행중인 미션 목록 조회 API */
    @GetMapping("/{userId}/missions/in-progress")
    @Override
    public ApiResponse<MissionResDTO.UserMissionPage> getInProgressMissions(
            @ExistUsers @PathVariable Long userId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.onSuccess(
                UserSuccessCode.USER_IN_PROGRESS_MISSION_LIST_FETCHED,
                userQueryService.getInProgressMissions(userId, page)
        );
    }

    /* 00-06 진행중인 미션 완료 처리 API */
    @PatchMapping("/{userId}/missions/{userMissionId}")
    @Override
    public ApiResponse<MissionResDTO.UserMissionInfo> completeMission(
            @ExistUsers @PathVariable Long userId,
            @PathVariable Long userMissionId
    ){
        return ApiResponse.onSuccess(
                UserSuccessCode.USER_MISSION_COMPLETED,
                userCommandService.completeMission(userId, userMissionId)
        );
    }



}
