package com.example.umc_9th_.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionRequest {

    public record SuccessMissionDTO(
            @NotNull String authCode
    ) {}
}