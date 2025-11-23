package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.member.exception.code.MemberSuccessCode;
import com.example.umc.domain.member.service.command.MemberCommandService;
import com.example.umc.domain.member.service.query.MemberQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping
    public ApiResponse<MemberResDto.JoinDto> signUp(@RequestBody @Valid MemberReqDto.JoinDto dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }


    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDto.GetMyPage> getMyPage(@PathVariable Long memberId) {
        MemberResDto.GetMyPage myPage = memberQueryService.getMyPage(memberId);

        MemberSuccessCode code = MemberSuccessCode.GET_MY_PAGE_SUCCESS;

        return ApiResponse.onSuccess(code, myPage);
    }

    @GetMapping("/{memberId}/missions")
    public ApiResponse<Page<MemberResDto.GetMemberMissions>> getMyMissions(@PathVariable Long memberId,
                                                                           @RequestParam(required = false) Long flag,
                                                                           @RequestParam MissionStatus status,
                                                                           Pageable pageable) {

        // flag 값에 따라 if문으로 서비스에 몰아놓으면 안좋을것같은데
        // 나중에 에러코드가 더 늘어났을 때 에러처리를 어떻게 하면 좋을지 생각해보자
        memberQueryService.checkFlag(flag);

        Page<MemberResDto.GetMemberMissions> myMissions = memberQueryService.getMyMissions(memberId, status, pageable);

        MemberSuccessCode getMissionsSuccess = MemberSuccessCode.GET_MISSIONS_SUCCESS;

        return ApiResponse.onSuccess(getMissionsSuccess, myMissions);
    }
}
