package com.example.umc9th_week5.domain.mission.service.command;

import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {
    MissionResDTO.MissionInfo addMission(MissionReqDTO.missionReqDTO dto);
}
