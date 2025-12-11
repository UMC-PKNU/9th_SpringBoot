package com.example.umc_9th_.domain.user.controller;

import com.example.umc_9th_.domain.review.converter.ReviewConverter;
import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.service.query.ReviewQueryService;
import com.example.umc_9th_.domain.user.converter.UserConverter;
import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.exception.code.UserSuccessCode;
import com.example.umc_9th_.domain.user.service.command.UserCommandService;
import com.example.umc_9th_.domain.user.service.query.UserQueryService;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.example.umc_9th_.global.config.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "유저 관련 API", description = "회원가입, 마이페이지, 홈 화면 조회 API") // ✅ Tag 추가
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final ReviewQueryService reviewQueryService;

    // 1. 회원가입 API
    @PostMapping("/users/signup")
    @Operation(summary = "회원가입 API", description = "새로운 사용자를 등록합니다.")
    public ApiResponse<UserResponse.JoinResultDTO> signup(@RequestBody @Valid UserRequest.JoinDTO request) {

        UserResponse.JoinResultDTO response = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserSuccessCode.USER_JOIN_SUCCESS, response);
    }

    // 1-1. 로그인 API (추가)
    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "이메일/비번으로 로그인하고 JWT 토큰을 발급받습니다.")
    public ApiResponse<UserResponse.LoginDTO> login(@RequestBody @Valid UserRequest.LoginDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userQueryService.login(request));
    }

    // 2. 사용자 위치 수정 API
    @PutMapping("/me/location")
    public ApiResponse<UserResponse.LocationUpdateDTO> updateLocation(
            @RequestBody @Valid UserRequest.UpdateLocationDTO request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) { // ✅ 타입 변경

        UserResponse.LocationUpdateDTO response = userCommandService.updateLocation(userDetails.getUser().getId(), request);
        return ApiResponse.onSuccess(UserSuccessCode.USER_LOCATION_UPDATE_SUCCESS, response);
    }

    // 3. 마이페이지 기본 정보 조회 API
    @GetMapping("/me/mypage")
    public ApiResponse<UserResponse.MyPageInfoDTO> getMyPage(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) { // ✅ 타입 변경

        UserResponse.MyPageInfoDTO response = userQueryService.getMyPageInfo(userDetails.getUser().getId());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 4. 홈 화면 미션 달성도 조회 API
    @GetMapping("/home/mission-summary")
    public ApiResponse<UserResponse.MissionSummaryDTO> getMissionSummary(
            @RequestParam String location,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) { // ✅ 타입 변경

        UserResponse.MissionSummaryDTO response = userQueryService.getMissionSummary(location, userDetails.getUser().getId());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 5. 홈 화면에서 내 미션 보는 API
    @GetMapping("/home/my-missions")
    public ApiResponse<UserResponse.MyMissionPageDTO> getMyMissions(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails, // ✅ 타입 변경
            @PageableDefault(size = 10) Pageable pageable) {

        Page<UserMission> missionPage = userQueryService.getUserMissions(userDetails.getUser().getId(), pageable);
        UserResponse.MyMissionPageDTO response = UserConverter.toMyMissionPageDTO(missionPage);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 6. 마이페이지 리뷰 보기 API
    @GetMapping("/me")
    public ApiResponse<ReviewResponse.MyReviewPageDTO> getMyReviews(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails, // ✅ 타입 변경
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingRange,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Review> reviewPage = reviewQueryService.getMyReviews(userDetails.getUser().getId(), storeName, ratingRange, pageable);
        ReviewResponse.MyReviewPageDTO response = ReviewConverter.toMyReviewPageDTO(reviewPage);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}