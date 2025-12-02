package com.example.umc_9th_.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    public record MissionStartDTO(
            Long missionId,
            String currentStatus,
            LocalDateTime startedAt
    ) {}

    public record MissionDTO(
            Long missionId,
            String missionTitle,
            String missionCondition,
            Integer rewardPoint,
            String storeName
    ) {}

    public record MissionPageDTO(
            List<MissionDTO> missionList,
            Integer currentPage,
            Integer totalPages,
            Long totalElements,
            Integer pageSize,
            Boolean isFirst,
            Boolean isLast
    ) {}

    public record MissionSuccessResultDTO(
            Long missionId,
            String finalStatus,
            Integer earnedPoint,
            Long reviewIdToRegister
    ) {}
}