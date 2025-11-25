package com.example.umc9th_week5.domain.mission.dto.req;

import lombok.Builder;

public class MemberMissionReqDTO {
    @Builder
    public record memberMissionReqDTOForChallenge(
            Long memberId,
            Long missionId
    ){}
}
