package com.example.umc_9th_spring.domain.mission.service.command;

import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {
    /* 01-04 미션 받기 */
    MissionResDTO.MissionInfo receiveMission(Long userId, Long missionId);

    /* 01-03 미션 성공 처리 */
    MissionResDTO.MissionInfo completeMission(Long userId, Long missionId);
}
