package com.example.umc9th_week5.domain.member.dto.res;

import com.example.umc9th_week5.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResDto {
    private Long memberId;
    private String name;

    public static MemberResDto from(Member member) {
        return MemberResDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .build();
    }
}
