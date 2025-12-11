package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.member.exception.code.MemberSuccessCode;
import com.example.umc.domain.member.service.command.MemberCommandService;
import com.example.umc.domain.member.service.query.MemberQueryService;
import com.example.umc.domain.review.dto.res.ReviewResDto;
import com.example.umc.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc.domain.review.service.query.ReviewQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController implements MemberControllerDocs {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;

    // 9주차 미션1. 리뷰 목록 조회
    // 특정 가게에 작성한 리뷰 목록도 가져올 수 있게 파라미터로 가게이름을 받을 수 있게 했다.
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResDto.ReviewPreViewListDto> getReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam Integer page) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;

        return ApiResponse.onSuccess(code, reviewQueryService.findReview(memberId, storeName, page));
    }

    // 9주차 미션3. 내가 진행중인 미션 목록
    // status 값으로 IN_PROGRESS를 받으면 진행중인 미션 목록을 확인할 수 있다.
    @GetMapping("/{memberId}/missions")
    public ApiResponse<MemberMissionResDto.GetMemberMissionsDto> getMyMissions(@PathVariable Long memberId,
//                                                                           @RequestParam(required = false) Long flag,
                                                                               @RequestParam MissionStatus status,
                                                                               @RequestParam Integer page) {

        // flag 값에 따라 if문으로 서비스에 몰아놓으면 안좋을것같은데
        // 나중에 에러코드가 더 늘어났을 때 에러처리를 어떻게 하면 좋을지 생각해보자
//        memberQueryService.checkFlag(flag);

        MemberMissionResDto.GetMemberMissionsDto myMissions = memberQueryService.getMyMissions(memberId, status, page);

        MemberSuccessCode getMissionsSuccess = MemberSuccessCode.GET_MISSIONS_SUCCESS;

        return ApiResponse.onSuccess(getMissionsSuccess, myMissions);
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDto.JoinDto> signUp(@RequestBody @Valid MemberReqDto.JoinDto dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberCommandService.signup(dto));
    }

    // 브라우저에서 /members/login으로 들어왔을 때 리다이렉트
//    @GetMapping("/login")
//    public void loginPage(HttpServletResponse response) throws IOException, IOException {
//        // 스프링 시큐리티의 기본 로그인 페이지(/login)로 강제 이동시킵니다.
//        response.sendRedirect("/members/login");
//    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDto.LoginDto> login(@RequestBody @Valid MemberReqDto.LoginDto dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }


    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDto.GetMyPage> getMyPage(@PathVariable Long memberId) {
        MemberResDto.GetMyPage myPage = memberQueryService.getMyPage(memberId);

        MemberSuccessCode code = MemberSuccessCode.GET_MY_PAGE_SUCCESS;

        return ApiResponse.onSuccess(code, myPage);
    }
}
