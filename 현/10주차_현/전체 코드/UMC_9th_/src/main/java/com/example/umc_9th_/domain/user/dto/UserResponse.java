package com.example.umc_9th_.domain.user.dto;

import com.example.umc_9th_.domain.user.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {

    // 회원가입 결과
    public record JoinResultDTO(
            Long userId,
            LocalDateTime createdAt
    ) {}

    // 위치 수정 결과
    public record LocationUpdateDTO(
            Long userId,
            String newLocation,
            LocalDateTime updatedAt
    ) {}

    // 마이페이지 정보
    public record MyPageInfoDTO(
            Long userId,
            String nickname,
            String email,
            Integer point
    ) {}

    // 미션 요약 정보
    public record MissionSummaryDTO(
            Integer totalMissionCount,
            Integer completedMissionCount,
            Integer inProgressMissionCount,
            Integer currentPoint
    ) {}

    // 내가 진행 중인 미션 목록 (페이징 포함)
    public record MyMissionPageDTO(
            List<MyMissionDTO> result,
            Long totalElements,
            Integer totalPage,
            Boolean isFirst,
            Boolean isLast,
            Integer currentPage
    ) {}

    // 개별 미션 정보
    public record MyMissionDTO(
            Long id,
            String storeName,
            Integer reward,
            String missionSpec,
            MissionStatus status,
            LocalDateTime completedAt
    ) {}

    @Builder
    public record LoginDTO(
            Long userId,
            String accessToken,
            String nickname
    ) {}
}