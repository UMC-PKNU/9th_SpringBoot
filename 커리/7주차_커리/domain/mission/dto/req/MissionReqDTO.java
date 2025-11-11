package com.example.umc_9th_spring.domain.mission.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionReqDTO {

    @Getter
    @Builder
    public static class CreateMission {
        private Long storeId;
        private String title;
        private String description;
        private LocalDateTime deadline;
        private Integer rewardPoint;
    }

    @Getter
    @Builder
    public static class UpdateMission {
        private Long missionId;
        private String title;
        private String description;
        private LocalDateTime deadline;
        private Integer rewardPoint;
    }
}
