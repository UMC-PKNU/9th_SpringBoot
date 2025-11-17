package com.example.umc9th.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {
    @Builder
    @Getter
    public static class MemberResIdNicknameDTO{
        private final Long id;
        private final String nickname;
    }
}
