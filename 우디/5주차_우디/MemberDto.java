package com.example.umc.domain.member;

import com.example.umc.domain.member.entity.Member;
import lombok.*;

@Getter
@Builder
public class MemberDto {

    private String nickname;
    private String email;
    private String phoneNumber;
    private Long point;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
