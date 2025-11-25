package com.example.umc9th_week5.domain.member.converter;

import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.MemberInfo toMemberDTO(Member member){
        return MemberResDTO.MemberInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .build();
    }

    // dto -> entity
    public static Member toMemberEntity(MemberReqDTO.memberReqDTO dto){
        return Member.builder()
                .address(dto.address())
                .birth(dto.birth())
                .gender(dto.gender())
                .name(dto.name())
                .build();
    }
}
