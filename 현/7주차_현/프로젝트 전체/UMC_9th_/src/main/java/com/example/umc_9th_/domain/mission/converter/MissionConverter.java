package com.example.umc_9th_.domain.mission.converter;

import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.entity.Mission;

public class MissionConverter {

    // Mission Entity -> MissionDTO (홈 화면 미션 조회용)
    public static MissionResponse.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponse.MissionDTO.builder()
                .missionId(mission.getId())
                .missionTitle(mission.getMissionTitle())
                .missionCondition(mission.getMissionCondition())
                .rewardPoint(mission.getRewardPoint())
                .storeName(mission.getStore().getStoreName())
                .build();
    }
}