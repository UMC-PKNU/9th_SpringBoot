package com.example.umc_9th_.domain.mission.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

public class MissionRequest {

    @Getter
    public static class SuccessMissionDTO {
        @NotNull
        private String authCode; // 상점 인증 코드
    }
}