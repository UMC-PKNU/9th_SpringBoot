package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc9th.domain.member.service.MemberService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode; // 성공 코드
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MemberController {

    private final MemberService memberService;
    // 토큰 방식
    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDto dto
    ){
        Member member = memberService.joinMember(dto);
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, MemberConverter.toJoinResultDTO(member));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberService.login(dto));
    }

    // 세션 방식
//    @PostMapping("/auth/users/register")
//    public ApiResponse<MemberResDTO.JoinResultDTO> join(
//            @RequestBody MemberReqDTO.JoinDto request
//    ) {
//        // 1. 서비스 로직 호출 (Member 엔티티 생성)
////        Member member = memberService.joinMember(request);
//
//        // 2. 응답 DTO 생성 및 반환
//        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberService.joinMember(request));
//    }
    // 홈 화면 조회 API
    @GetMapping("/users/{userId}/home")
    public ApiResponse<MemberResDTO.HomeViewDTO> getHome(@PathVariable(name = "userId") Long userId) {

        // 1. 유저 정보 가져오기 (닉네임, 포인트 등)
        Member member = memberService.findMember(userId);

        // 2. 진행 중인 미션 목록 가져오기
        List<MemberMission> myMissions = memberService.findMyProgressMissions(userId);

        // 3. 완료한 미션 개수 가져오기 (7/10 계산용)
        Integer completedCount = memberService.getCompletedMissionCount(userId);

        // 4. 변환 및 응답
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberConverter.toHomeViewDTO(member, myMissions, completedCount)
        );
    }

}