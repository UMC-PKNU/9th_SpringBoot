package com.example.umc_9th_spring.domain.mission.converter;

import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;

public class MissionConverter {

    // 요청 DTO -> Entity
    public static Mission toEntity(MissionReqDTO.CreateMission dto, Store store) {
        return Mission.builder()
                .store(store)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .deadline(dto.getDeadline())
                .rewardPoint(dto.getRewardPoint())
                .build();
    }

    // Entity -> 응답 DTO (리스트 조회)
    public static MissionResDTO.MissionInfo toMissionInfoDTO(Mission mission) {
        return MissionResDTO.MissionInfo.builder()
                .id(mission.getId())
                .storeName(mission.getStore().getName())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .deadline(mission.getDeadline())
                .rewardPoint(mission.getRewardPoint())
                .build();
    }

    // Entity -> 응답 DTO (요약용)
    public static MissionResDTO.MissionSummary toMissionSummaryDTO(Mission mission, boolean isCompleted) {
        return MissionResDTO.MissionSummary.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .isCompleted(isCompleted)
                .build();
    }

    public static MissionResDTO.MissionInfo toMissionInfoDTO(UserMissionMap map) {
        return MissionResDTO.MissionInfo.builder()
                .id(map.getMission().getId())
                .storeName(map.getMission().getStore().getName())
                .title(map.getMission().getTitle())
                .description(map.getMission().getDescription())
                .deadline(map.getMission().getDeadline())
                .rewardPoint(map.getMission().getRewardPoint())
                .build();
    }
}
