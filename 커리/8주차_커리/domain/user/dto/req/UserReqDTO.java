package com.example.umc_9th_spring.domain.user.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class UserReqDTO {

    @Getter
    @Builder
    public static class SignUp {
        @NotNull
        private String username;
        @Email
        private String email;
        @NotNull
        private String password;
        @NotNull
        private String phone;
    }

    @Getter
    @Builder
    public static class Login {
        @Email
        private String email;
        @NotNull
        private String password;
    }
}
