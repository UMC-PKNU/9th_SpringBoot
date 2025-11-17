package com.example.umc.domain.member.dto.req;

import lombok.Builder;
import lombok.Getter;

public class MemberReqDto {

    @Getter
    @Builder
    public static class LoginRequest {
        String email;
        String password;
    }
}
