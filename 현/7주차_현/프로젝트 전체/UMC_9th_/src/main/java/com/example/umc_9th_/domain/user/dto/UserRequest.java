package com.example.umc_9th_.domain.user.dto;

import com.example.umc_9th_.domain.user.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

public class UserRequest {

    @Getter
    public static class JoinDTO {
        @NotNull
        private String name;
        @NotNull
        private String nickname;
        @NotNull
        private Gender gender;
        @NotNull
        private LocalDate birthDate;
        @NotNull
        private String address; // 초기 위치 주소
        @NotNull @Email
        private String email;
        private String phoneNum;
        @NotNull
        private List<Long> preferredCategoryIds; // 선호 카테고리 ID 목록
        @NotNull
        private List<Long> agreedTermIds; // 동의한 약관 ID 목록
    }

    @Getter
    public static class UpdateLocationDTO {
        @NotNull
        private String address; // 새로운 위치 (동/Location)
    }
}