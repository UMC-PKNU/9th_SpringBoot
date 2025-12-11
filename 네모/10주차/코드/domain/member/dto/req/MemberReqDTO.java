package com.example.umc9th_week5.domain.member.dto.req;

import com.example.umc9th_week5.domain.member.enums.Gender;
import com.example.umc9th_week5.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    @Builder
    public record memberReqDTO(
       String name,
       Gender gender,
       LocalDate birth,
       String address
    ){}

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String nickname,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @ExistFoods
            List<Long> preferCategory
    ){}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}