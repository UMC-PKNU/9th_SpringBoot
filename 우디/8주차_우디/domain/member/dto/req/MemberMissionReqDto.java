package com.example.umc.domain.member.dto.req;

import jakarta.validation.constraints.NotNull;

public class MemberMissionReqDto {

    public record ChallengeMissionDto (
            @NotNull
            Long missionId,
            @NotNull
            Long memberId
    ){}
}
