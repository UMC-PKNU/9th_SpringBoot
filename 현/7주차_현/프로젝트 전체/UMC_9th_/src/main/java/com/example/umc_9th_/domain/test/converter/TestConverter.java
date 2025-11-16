package com.example.umc_9th_.domain.test.converter;

import com.example.umc_9th_.domain.test.dto.TestResDTO;

public class TestConverter {

    // 객체 -> DTO
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResDTO.Testing.builder()
                .testing(testing)
                .build();
    }
}