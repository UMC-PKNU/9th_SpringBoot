package com.example.umc_9th_spring.domain.mission.dto.req;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionReqDTO {

    @Getter
    @Builder
    public static class CreateMission {
        private Long storeId;

        @NotBlank(message = "미션 제목은 필수입니다.")
        private String title;

        @NotBlank(message = "미션 설명은 필수입니다.")
        private String description;

        @NotNull(message = "마감 기한은 필수입니다.")
        @Future(message = "마감 시간은 현재 시간 이후여야 합니다.")
        private LocalDateTime deadline;

        @NotNull(message = "포인트는 필수입니다.")
        @Positive(message = "포인트는 0보다 커야 합니다.")
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
