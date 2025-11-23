package com.example.umc9th_week5.domain.mission.dto.req;

import com.example.umc9th_week5.domain.member.dto.req.MemberReqDTO;

public class MemberMissionReqDTO {
    public record memberMissionReqDTOForChallenge(
            MemberReqDTO.memberReqDTOForChallenge member,
            MissionReqDTO.missionReqDTOForChallenge mission
    ){}
}
