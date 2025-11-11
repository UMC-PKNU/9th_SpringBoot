package com.example.umc_9th_spring.domain.user.controller;


import com.example.umc_9th_spring.domain.inquiry.dto.res.InquiryResDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.user.code.UserSuccessCode;
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


    @GetMapping("/{userId}")
    public ApiResopnse<UserResDTO.UserInfo> getUserInfo(
            @PathVariable Long userId
    ){
        UserResDTO.UserInfo userInfo = userQueryService.getUserInfo(userId);
        return ApiResopnse.onSuccess(UserSuccessCode.USER_FETCH_SUCCESS, userInfo);
    }

    /* 유저가 작성한 리뷰 조회 */
    @GetMapping("/{userId}/reviews")
    public ApiResopnse<List<ReviewResDTO.ReviewSummary>> getUserReviews(@PathVariable Long userId) {
        List<ReviewResDTO.ReviewSummary> reviews = userQueryService.getUserReviews(userId);
        return ApiResopnse.onSuccess(UserSuccessCode.USER_REVIEW_LIST_FETCHED, reviews);
    }

    /* 유저가 작성한 문의 내역 조회 */
    @GetMapping("/{userId}/inquiries")
    public ApiResopnse<List<InquiryResDTO.InquirySummary>> getUserInquiries(@PathVariable Long userId) {
        List<InquiryResDTO.InquirySummary> inquiries = userQueryService.getUserInquiries(userId);
        return ApiResopnse.onSuccess(UserSuccessCode.USER_INQUIRY_LIST_FETCHED, inquiries);
    }

    /* 유저의 미션 상태별 조회 (페이징 지원) */
    @GetMapping("/{userId}/missions")
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
