package com.example.umc_9th_.domain.user.controller;

import com.example.umc_9th_.domain.review.dto.ReviewResponse;
import com.example.umc_9th_.domain.review.service.ReviewService;
import com.example.umc_9th_.domain.user.service.UserService;
import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;

    // 1. 회원가입 API: POST /api/users
    @PostMapping("/users/signup")
    public ApiResponse<UserResponse.JoinResultDTO> signup(@RequestBody @Valid UserRequest.JoinDTO request) {

        UserResponse.JoinResultDTO response = userService.joinUser(request);

        return ApiResponse.onSuccess(GeneralSuccessCode.USER_JOIN_SUCCESS, response); // USER_JOIN_SUCCESS 사용
    }

    // 2. 사용자 위치 수정(드롭다운) API: PUT /api/me/location
    @PutMapping("/me/location")
    public ApiResponse<UserResponse.LocationUpdateDTO> updateLocation(
            @RequestBody @Valid UserRequest.UpdateLocationDTO request,
            @AuthenticationPrincipal Long userId) { // 실제 사용자 ID를 Security Context에서 가져옴

        UserResponse.LocationUpdateDTO response = userService.updateLocation(userId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.USER_LOCATION_UPDATE_SUCCESS, response);
    }

    // 3. 마이페이지 기본 정보 조회 API: GET /api/me/mypage
    @GetMapping("/me/mypage")
    public ApiResponse<UserResponse.MyPageInfoDTO> getMyPage(@AuthenticationPrincipal Long userId) {

        UserResponse.MyPageInfoDTO response = userService.getMyPageInfo(userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 4. 홈 화면 미션 달성도 나타내는 API: GET /api/home/mission-summary
    @GetMapping("/home/mission-summary")
    public ApiResponse<UserResponse.MissionSummaryDTO> getMissionSummary(
            @RequestParam String location,
            @AuthenticationPrincipal Long userId) {

        UserResponse.MissionSummaryDTO response = userService.getMissionSummary(location, userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 5. 홈 화면에서 내 미션 보는 API: GET /api/home/my-missions
    // 이 API는 MissionController의 getAvailableMissions와 로직이 비슷함 << 생각좀 더해보자

    // 6. 마이페이지 리뷰 보기 API: GET /api/me/reviews
    @GetMapping("/me") // /api/reviews/me (실제 API: GET /api/me/reviews)
    public ApiResponse<ReviewResponse.MyReviewPageDTO> getMyReviews(
            @AuthenticationPrincipal Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingRange,
            @PageableDefault(size = 10) Pageable pageable) {

        ReviewResponse.MyReviewPageDTO response = reviewService.getMyReviews(userId, storeName, ratingRange, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_LOOKUP_SUCCESS, response);
    }
}