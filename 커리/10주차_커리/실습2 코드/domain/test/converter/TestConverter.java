package com.example.umc_9th_spring.domain.test.converter;

import com.example.umc_9th_spring.domain.test.dto.res.TestResDTO;

public class TestConverter {

    // Service층에서 엔티티 객체 생성, 저장하고 -> DTO로 변환할 때,
    // TestConverter.toTestingDTO(email, password) 이걸로 받아먹으면 됨.
    public static TestResDTO.TestSignupDto toTestingDTO(
            String email,
            String password
    ){
        return TestResDTO.TestSignupDto.builder()
                .email(email)
                .password(password)
                .build();
    }

    public static TestResDTO.Exception toExceptionDTO(String message){
        return TestResDTO.Exception.builder()
                .message(message)
                .build();
    }

}
