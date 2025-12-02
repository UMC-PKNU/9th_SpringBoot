package com.example.umc_9th_.domain.store.dto;

import java.time.LocalDateTime;
import java.util.List;

public class StoreResponse {

    public record JoinResultDTO(
            Long storeId,
            LocalDateTime createdAt
    ) {}

    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    public record CreateMissionResultDTO(
            Long missionId,
            LocalDateTime createdAt
    ) {}

    public record CreateUserMissionResultDTO(
            Long userMissionId,
            LocalDateTime createdAt
    ) {}
    // 가게 상세 [추가]
    public record StoreDetailDTO(
            Long storeId,
            String storeName,
            String address,
            String region
    ) {}

    // 미션 목록 조회 (페이징) [추가]
    public record MissionListDTO(
            List<MissionDTO> result,
            Long totalElements,
            Integer totalPage,
            Boolean isFirst,
            Boolean isLast,
            Integer currentPage
    ) {}

    // 개별 미션 정보 [추가]
    public record MissionDTO(
            Long id,
            String missionSpec,
            Integer reward,
            String storeName
    ) {}
}