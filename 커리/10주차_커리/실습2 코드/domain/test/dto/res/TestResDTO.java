package com.example.umc_9th_spring.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    // 도메인 관련 큰 DTO 만들고, (ex - UserResDTO, ReveiwResDTO)
    // 이후 static 클래스로 내부적으로 UserLoginDTO, UserSignUpReqDTO. . 등등을 만들면
    // 객체 -> DTO로 변환해주는 converter를 만들 때 toTestingDTO() 메서드 호출만 하면 되도록 만들 수있음. static이라 바로 접근 가능
    @Builder
    @Getter
    public static class TestSignupDto {
        private String email;
        private String password;
    }

    @Builder
    @Getter
    public static class TestLoginDto {
        private String username;
        private String email;
        private String password;
    }

    @Builder
    @Getter
    public static class Exception {
        private String message;
    }

}
