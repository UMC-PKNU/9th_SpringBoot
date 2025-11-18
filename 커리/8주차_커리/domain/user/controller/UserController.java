package com.example.umc_9th_spring.domain.user.controller;


import com.example.umc_9th_spring.domain.inquiry.dto.res.InquiryResDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.user.code.UserSuccessCode;
import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.service.command.UserCommandService;
import com.example.umc_9th_spring.domain.user.service.query.UserQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;


    /* 00-01 회원가입 API */
    @PostMapping
    public ApiResopnse<UserResDTO.UserInfo> userSignUp(
        @RequestBody UserReqDTO.SignUp request
    ){
        UserResDTO.UserInfo response = userCommandService.userSignUp(request);
        return ApiResopnse.onSuccess(UserSuccessCode.USER_SIGNUP_CREATED, response);
    }

    /* 00-02 홈 화면 조회 API */
    @GetMapping("/{userId}")
    public ApiResopnse<UserResDTO.UserInfo> getUserInfo(
            @PathVariable Long userId
    ){
        UserResDTO.UserInfo userInfo = userQueryService.getUserInfo(userId);
        return ApiResopnse.onSuccess(UserSuccessCode.USER_FETCH_SUCCESS, userInfo);
    }

    /* 00-03 유저의 미션 상태별 조회 API (페이징 지원) */
    @GetMapping("/{userId}/userMissionMap")
    public ApiResopnse<Page<MissionResDTO.MissionInfo>> getUserMissionsByStatus(
            @PathVariable Long userId,
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MissionResDTO.MissionInfo> missions =
                userQueryService.getUserMissionsByStatus(userId, status, page, size);
        return ApiResopnse.onSuccess(UserSuccessCode.USER_MISSION_LIST_FETCHED, missions);
    }

}
