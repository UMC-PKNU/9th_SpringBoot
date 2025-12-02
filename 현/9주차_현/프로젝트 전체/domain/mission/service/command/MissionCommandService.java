package com.example.umc_9th_.domain.mission.service.command;

import com.example.umc_9th_.domain.mission.dto.MissionRequest;
import com.example.umc_9th_.domain.mission.dto.MissionResponse;

public interface MissionCommandService {
    MissionResponse.MissionStartDTO startMission(Long missionId, Long userId);
    MissionResponse.MissionSuccessResultDTO successMission(Long missionId, Long userId, MissionRequest.SuccessMissionDTO request);
}