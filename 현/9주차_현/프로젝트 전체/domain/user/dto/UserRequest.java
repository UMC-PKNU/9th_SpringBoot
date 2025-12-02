package com.example.umc_9th_.domain.user.dto;

import com.example.umc_9th_.domain.user.enums.Gender;
import com.example.umc_9th_.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class UserRequest {

    // 회원가입 요청
    public record JoinDTO(
            @NotBlank String name,
            @NotNull String nickname,
            @NotNull Gender gender,
            @NotNull LocalDate birthDate,
            @NotNull String address,
            @NotBlank @Email String email,
            String phoneNum,
            @ExistFoods List<Long> preferredCategoryIds,
            @NotNull List<Long> agreedTermIds
    ) {}

    // 위치 수정 요청
    public record UpdateLocationDTO(
            @NotNull String address
    ) {}
}