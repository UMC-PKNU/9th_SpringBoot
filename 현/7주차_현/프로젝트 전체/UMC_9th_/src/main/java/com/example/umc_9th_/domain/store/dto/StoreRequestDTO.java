package com.example.umc_9th_.domain.store.dto;

import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StoreRequestDTO {

    // 1. 가게 추가
    @Getter
    public static class JoinDto {
        private String name;
        private String address;
        private String region;
        private Long categoryId;
    }

    // 2. 리뷰 추가
    @Getter
    public static class ReviewDto {
        private Long storeId;
        private Long missionId; // 리뷰 엔티티에 미션이 필수라서 추가
        private String content;
        private Double rating;
    }

    // 3. 미션 추가
    @Getter
    public static class MissionDto {
        private Long storeId;
        private Integer rewardPoint;
        private LocalDate deadline; 
        private String missionTitle;
        private String missionCondition;
    }

    // 4. 미션 도전
    @Getter
    public static class ChallengeMissionDto {
        private Long missionId;
    }
}