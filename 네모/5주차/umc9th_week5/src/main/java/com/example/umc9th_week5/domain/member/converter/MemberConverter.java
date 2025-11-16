package com.example.umc9th_week5.domain.member.converter;

import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.memberInfo toMemberDTO(Member member){
        return MemberResDTO.memberInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .build();
    }
}
