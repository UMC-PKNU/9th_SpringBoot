package com.example.umc9th_week5.domain.mission.service.query;

import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionQueryService {
    MemberMissionResDTO.MemberMissionInfoList getInProgressMissions(Long memberId, int page);
}
