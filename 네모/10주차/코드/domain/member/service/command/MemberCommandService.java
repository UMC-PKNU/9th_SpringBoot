package com.example.umc9th_week5.domain.member.service.command;

import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.MemberInfo signup(MemberReqDTO.JoinDTO dto);
}
