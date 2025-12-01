package com.example.umc.domain.member.dto.req;

import com.example.umc.domain.member.enums.Gender;
import com.example.umc.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberReqDto {

    public record JoinDto (
            @NotBlank
            String name,
            @NotBlank
            Gender gender,
            @NotBlank
            String birth,
            @NotBlank
            String address,
            @NotBlank
            String position,
//            엔티티를 직접 받으면 안됨
//            List<Food> preferFood
            @ExistFoods
            List<Long> preferFoodIds
    ){}


    @Getter
    @Builder
    public static class LoginRequest {
        String email;
        String password;
    }
}
