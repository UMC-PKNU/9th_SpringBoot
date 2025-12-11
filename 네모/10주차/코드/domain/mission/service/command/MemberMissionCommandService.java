package com.example.umc9th_week5.domain.mission.service.command;

import com.example.umc9th_week5.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {
    MemberMissionResDTO.MemberMissionInfo missionChallenge(MemberMissionReqDTO.memberMissionReqDTOForChallenge dto);
    MemberMissionResDTO.MemberMissionInfoList completeMission(Long memberId, Long missionId, int page);
}
