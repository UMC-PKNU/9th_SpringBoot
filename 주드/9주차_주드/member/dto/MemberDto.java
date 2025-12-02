// MemberDto.java

package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {
    private final Long id;
    private final String nickname;

    // 엔티티를 DTO로 변환하는 팩토리 메서드
    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .build();
    }

}