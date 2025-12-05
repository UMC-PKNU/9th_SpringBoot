package com.example.umc9th.domain.member.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberMissionReqDTO {
    @Getter
    public static class MissionAcceptDto {
        @NotNull
        private Long missionId;
    }
}
