package com.example.umc9th_week5.domain.member.converter;

import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.member.enums.Gender;
import com.example.umc9th_week5.global.annotation.ExistFoods;
import com.example.umc9th_week5.global.enums.Role;

import java.time.LocalDate;
import java.util.List;

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

    public static Member toMemberEntityForSignUp(MemberReqDTO.JoinDTO dto, String password, Role role){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .nickname(dto.nickname())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}