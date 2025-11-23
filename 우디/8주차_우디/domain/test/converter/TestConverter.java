package com.example.umc.domain.test.converter;

import com.example.umc.domain.test.dto.res.TestResponseDto;

public class TestConverter {

    // 객체 -> DTO
    public static TestResponseDto.Testing toTestingDto(String testing) {
        return TestResponseDto.Testing.builder()
                .testing(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResponseDto.Exception toExceptionDto(String testing) {
        return TestResponseDto.Exception.builder()
                .testString(testing)
                .build();
    }
}
