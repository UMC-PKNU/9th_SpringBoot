package com.example.umc_9th_spring.domain.mission.converter;

import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import org.springframework.data.domain.Page;

import java.util.List;

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

    /* 1) UserMissionMap -> UserMissionInfo (진행중 조회, 완료 API) */
    public static MissionResDTO.UserMissionInfo toUserMissionInfo(UserMissionMap map) {
        return MissionResDTO.UserMissionInfo.builder()
                .userMissionId(map.getId())
                .missionId(map.getMission().getId())
                .missionTitle(map.getMission().getTitle())
                .storeName(map.getMission().getStore().getName())
                .rewardPoint(map.getMission().getRewardPoint())
                .status(map.getStatus().name())
                .build();
    }

    /* 2) Page<UserMissionMap> -> UserMissionPage */
    public static MissionResDTO.UserMissionPage toUserMissionPage(Page<UserMissionMap> page) {

        List<MissionResDTO.UserMissionInfo> missions = page.getContent()
                .stream()
                .map(MissionConverter::toUserMissionInfo)
                .toList();

        return MissionResDTO.UserMissionPage.builder()
                .missions(missions)
                .page(page.getNumber() + 1)    // 0 → 1
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    /* 3) Mission -> StoreMissionInfo */
    public static MissionResDTO.StoreMissionInfo toStoreMissionInfo(Mission mission) {
        return MissionResDTO.StoreMissionInfo.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .rewardPoint(mission.getRewardPoint())
                .build();
    }

    /* 4) Page<Mission> -> StoreMissionPage */
    public static MissionResDTO.StoreMissionPage toStoreMissionPage(Page<Mission> page) {

        List<MissionResDTO.StoreMissionInfo> missions = page.getContent()
                .stream()
                .map(MissionConverter::toStoreMissionInfo)
                .toList();

        return MissionResDTO.StoreMissionPage.builder()
                .missions(missions)
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

}
