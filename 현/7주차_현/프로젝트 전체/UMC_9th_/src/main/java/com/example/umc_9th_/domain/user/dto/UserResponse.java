package com.example.umc_9th_.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

public class UserResponse {

    @Getter
    @Builder
    public static class JoinResultDTO {
        private Long userId;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class LocationUpdateDTO {
        private Long userId;
        private String newLocation;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class MyPageInfoDTO {
        // UserRepository의 findUserMyPageInfo 생성자에 맞춰 필드 구성
        private Long userId;
        private String nickname;
        private String email;
        private Integer point; // userPoint
        // 미션 달성도는 별도 API (Mission Summary)로 가져오는 것이 더 깔끔하나, 현재는 기본 정보만 포함
    }

    @Getter
    @Builder
    public static class MissionSummaryDTO {
        private Integer totalMissionCount;
        private Integer completedMissionCount;
        private Integer inProgressMissionCount;
        private Integer currentPoint;
        // ... (달성률 등 추가 가능)
    }
}