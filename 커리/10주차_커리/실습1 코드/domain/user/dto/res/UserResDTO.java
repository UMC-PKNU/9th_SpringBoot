package com.example.umc_9th_spring.domain.user.dto.res;

import com.example.umc_9th_spring.global.annotation.ExistUsers;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class UserResDTO {

    @Getter
    @Builder
    public static class UserInfo {
        @ExistUsers
        private Long id;
        @NotNull
        private String username;
        @Email
        private String email;
    }

    @Getter
    @Builder
    public static class LoginResult {
        @NotNull
        private String token;
        @NotNull
        private String username;
    }
}
