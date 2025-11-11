package com.example.umc_9th_spring.domain.user.dto.res;

import lombok.Builder;
import lombok.Getter;

public class UserResDTO {

    @Getter
    @Builder
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
    }

    @Getter
    @Builder
    public static class LoginResult {
        private String token;
        private String username;
    }
}
