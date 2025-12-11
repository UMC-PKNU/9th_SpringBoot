package com.example.umc9th_week5.domain.member.controller;

import com.example.umc9th_week5.domain.member.code.MemberSuccessCode;
import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.service.command.MemberCommandService;
import com.example.umc9th_week5.domain.member.service.query.MemberQueryService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController implements MemberControllerDocs{
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.MemberInfo> signUp(@RequestBody @Valid MemberReqDTO.JoinDTO dto){
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberCommandService.signup(dto));
    }

    @PostMapping("login")
    public ApiResponse<MemberResDTO.LoginDTO> login(@RequestBody @Valid MemberReqDTO.LoginDTO dto){
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberQueryService.login(dto));
    }

}
