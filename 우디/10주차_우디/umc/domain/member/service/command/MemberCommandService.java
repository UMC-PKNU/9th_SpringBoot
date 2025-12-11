package com.example.umc.domain.member.service.command;

import com.example.umc.domain.member.dto.req.MemberReqDto;
import com.example.umc.domain.member.dto.res.MemberResDto;

public interface MemberCommandService {

    // 회원가입
    MemberResDto.JoinDto signup(MemberReqDto.JoinDto dto);

}
