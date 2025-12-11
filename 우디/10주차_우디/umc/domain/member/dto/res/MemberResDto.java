package com.example.umc.domain.member.dto.res;

import com.example.umc.domain.member.enums.MissionStatus;
import com.example.umc.domain.food.enums.FoodCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDto {

    // 10주차
    // 로그인
    @Builder
    public record LoginDto (
            Long memberId,
            String accessToken
    ){}

    @Builder
    public record JoinDto (
            Long memberId,
            LocalDateTime createAt
    ){}

    @Getter
    @Builder
    public static class GetMyPage {
        private String nickname;
        private String email;
        private String phoneNumber;
        private Long point;
    }

    @Getter
    @Builder
    public static class AvailableMission {
        private String nickname;
        private FoodCategory foodCategory;
        private int moneyLowerLimit;
        private int period;
        private MissionStatus missionStatus;
    }

}
