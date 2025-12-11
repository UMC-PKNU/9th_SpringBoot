package com.example.umc.domain.member.service.command;

import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.mission.dto.MissionReqDto;

public interface MemberMissionCommandService {

    MemberMissionResDto.CreateResultDto challengeMission(Long memberId, MissionReqDto.ChallengeMissionDto dto);
}
