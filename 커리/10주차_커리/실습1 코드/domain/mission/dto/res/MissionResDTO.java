package com.example.umc_9th_spring.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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


    @Getter
    @Builder
    public static class StoreMissionInfo {
        private Long missionId;
        private String title;
        private String description;
        private Integer rewardPoint;
    }

    @Getter
    @Builder
    public static class StoreMissionPage {
        private List<StoreMissionInfo> missions;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean first;
        private boolean last;
    }

    @Getter
    @Builder
    public static class UserMissionInfo {
        private Long userMissionId;
        private Long missionId;
        private String missionTitle;
        private String storeName;
        private String status;
        private Integer rewardPoint;
    }

    @Getter
    @Builder
    public static class UserMissionPage {
        private List<UserMissionInfo> missions;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean first;
        private boolean last;
    }

}
