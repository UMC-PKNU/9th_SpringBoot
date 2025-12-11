package com.example.umc9th_week5.domain.member.controller;

import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface MemberControllerDocs {

    @Operation(
            summary="회원가입",
            description="회원가입합시다"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberResDTO.MemberInfo> signUp(@RequestBody @Valid MemberReqDTO.JoinDTO dto);

    @Operation(
            summary="로그인",
            description="토큰 기반 로그인입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberResDTO.LoginDTO> login(@RequestBody @Valid MemberReqDTO.LoginDTO dto);
}
