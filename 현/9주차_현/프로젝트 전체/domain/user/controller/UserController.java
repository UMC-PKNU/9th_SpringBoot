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
import io.swagger.v3.oas.annotations.Parameters;
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

    // 2. 사용자 위치 수정 API
    @PutMapping("/me/location")
    @Operation(summary = "사용자 위치 수정 API", description = "사용자의 위치(주소)를 수정합니다.")
    public ApiResponse<UserResponse.LocationUpdateDTO> updateLocation(
            @RequestBody @Valid UserRequest.UpdateLocationDTO request,
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId) { // ✅ hidden=true 필수

        UserResponse.LocationUpdateDTO response = userCommandService.updateLocation(userId, request);
        return ApiResponse.onSuccess(UserSuccessCode.USER_LOCATION_UPDATE_SUCCESS, response);
    }

    // 3. 마이페이지 기본 정보 조회 API
    @GetMapping("/me/mypage")
    @Operation(summary = "마이페이지 조회 API", description = "마이페이지의 기본 정보(닉네임, 포인트 등)를 조회합니다.")
    public ApiResponse<UserResponse.MyPageInfoDTO> getMyPage(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId) { // ✅ hidden=true

        UserResponse.MyPageInfoDTO response = userQueryService.getMyPageInfo(userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 4. 홈 화면 미션 달성도 조회 API
    @GetMapping("/home/mission-summary")
    @Operation(summary = "홈 화면 미션 달성도 조회 API", description = "홈 화면 상단에 보여줄 미션 달성 현황을 조회합니다.")
    @Parameters({
            @Parameter(name = "location", description = "현재 사용자의 위치(지역)"),
    })
    public ApiResponse<UserResponse.MissionSummaryDTO> getMissionSummary(
            @RequestParam String location,
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId) { // ✅ hidden=true

        UserResponse.MissionSummaryDTO response = userQueryService.getMissionSummary(location, userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 5. 홈 화면에서 내 미션 보는 API
    @GetMapping("/home/my-missions")
    @Operation(summary = "진행 중인 내 미션 목록 API", description = "홈 화면 하단에 보여줄 내가 진행 중인 미션 목록을 조회합니다. (페이징 포함)")
    public ApiResponse<UserResponse.MyMissionPageDTO> getMyMissions(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId, // ✅ hidden=true
            @PageableDefault(size = 10) Pageable pageable) {

        Page<UserMission> missionPage = userQueryService.getUserMissions(userId, pageable);
        UserResponse.MyMissionPageDTO response = UserConverter.toMyMissionPageDTO(missionPage);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 6. 마이페이지 리뷰 보기 API
    @GetMapping("/me")
    @Operation(summary = "내가 쓴 리뷰 목록 API", description = "마이페이지에서 내가 작성한 리뷰 목록을 조회합니다. (페이징 포함)")
    @Parameters({
            @Parameter(name = "storeName", description = "가게 이름으로 검색 (선택)"),
            @Parameter(name = "ratingRange", description = "평점 점수대 검색 (선택)"),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작)")
    })
    public ApiResponse<ReviewResponse.MyReviewPageDTO> getMyReviews(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId, // ✅ hidden=true
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingRange,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Review> reviewPage = reviewQueryService.getMyReviews(userId, storeName, ratingRange, pageable);
        ReviewResponse.MyReviewPageDTO response = ReviewConverter.toMyReviewPageDTO(reviewPage);
        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_LOOKUP_SUCCESS, response);
    }
}