package com.example.umc.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDto {

    public record ChallengeMissionDto (
            @NotNull
            Long missionId
    ){}
}
