package com.example.umc_9th_.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    @Getter
    @Builder
    public static class MissionStartDTO {
        // 미션 시작 성공 응답
        private Long missionId;
        private String currentStatus;
        private LocalDateTime startedAt;
    }

    @Getter
    @Builder
    public static class MissionDTO {
        // 개별 미션 정보 (홈 화면)
        private Long missionId;
        private String missionTitle;
        private String missionCondition;
        private Integer rewardPoint;
        private String storeName;
    }

    @Getter
    @Builder
    public static class MissionPageDTO {
        // 미션 목록 (페이징 포함)
        private Integer currentPage;
        private Integer totalPages;
        private Long totalElements;
        private Integer pageSize;
        private List<MissionDTO> missionList;
    }

    @Getter
    @Builder
    public static class MissionSuccessResultDTO {
        private Long missionId;
        private String finalStatus; // COMPLETED
        private Integer earnedPoint; // 획득한 포인트
        private Long reviewIdToRegister; // 성공 후 작성해야 할 리뷰 ID
    }
}