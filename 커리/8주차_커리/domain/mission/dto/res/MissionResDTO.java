package com.example.umc_9th_spring.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionInfo {
        private Long id;
        private String storeName;
        private String title;
        private String description;
        private LocalDateTime deadline;
        private Integer rewardPoint;
    }

    @Getter
    @Builder
    public static class MissionSummary {
        private Long id;
        private String title;
        private boolean isCompleted;
    }

}
