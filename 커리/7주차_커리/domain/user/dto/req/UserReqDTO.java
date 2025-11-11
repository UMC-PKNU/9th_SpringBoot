package com.example.umc_9th_spring.domain.user.dto.req;

import lombok.Builder;
import lombok.Getter;

public class UserReqDTO {

    @Getter
    @Builder
    public static class SignUp {
        private String username;
        private String email;
        private String password;
        private String phone;
    }

    @Getter
    @Builder
    public static class Login {
        private String email;
        private String password;
    }
}
