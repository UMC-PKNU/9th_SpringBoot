package com.example.umc_9th_.domain.mission.converter;

import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponse.MissionDTO toMissionDTO(Mission mission) {
        return new MissionResponse.MissionDTO(
                mission.getId(),
                mission.getMissionTitle(),
                mission.getMissionCondition(),
                mission.getRewardPoint(),
                mission.getStore().getStoreName()
        );
    }

    public static MissionResponse.MissionPageDTO toMissionPageDTO(Page<Mission> missionPage) {
        List<MissionResponse.MissionDTO> missionList = missionPage.stream()
                .map(MissionConverter::toMissionDTO)
                .collect(Collectors.toList());

        return new MissionResponse.MissionPageDTO(
                missionList,
                missionPage.getNumber() + 1,
                missionPage.getTotalPages(),
                missionPage.getTotalElements(),
                missionPage.getSize(),
                missionPage.isFirst(),
                missionPage.isLast()
        );
    }
}