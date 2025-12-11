package com.example.umc_9th_.domain.store.dto;

import java.time.LocalDate;

public class StoreRequest {

    // 1. 가게 추가
    public record JoinDTO(
            String name,
            String address,
            String region,
            Long categoryId
    ) {}

    // 2. 리뷰 추가
    public record ReviewDTO(
            Long storeId,
            Long missionId,
            String content,
            Double rating
    ) {}

    // 3. 미션 추가
    public record MissionDTO(
            Long storeId,
            Integer rewardPoint,
            LocalDate deadline,
            String missionTitle,
            String missionCondition
    ) {}

    // 4. 미션 도전
    public record ChallengeMissionDTO(
            Long missionId
    ) {}

    // 미션 완료 요청 (바디에 받을 게 없으면 비워도 됨) [추가]
    public record CompleteMissionDTO(
            Long userMissionId
    ) {}
}