package com.example.umc9th_week5.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    public static class MemberInfo{
        private Long memberId;
        private String name;
        private String nickname;
    }

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}

}
