package com.example.umc_9th_.domain.store.converter;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.store.dto.StoreRequestDTO;
import com.example.umc_9th_.domain.store.dto.StoreResponseDTO;
import com.example.umc_9th_.domain.store.entity.FoodCategory;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.enums.MissionStatus; // Enum 패키지 확인 필요

import java.time.LocalDateTime;

public class StoreConverter {

    // Store 생성
    public static Store toStore(StoreRequestDTO.JoinDto request, FoodCategory category) {
        return Store.builder()
                .storeName(request.getName())
                .address(request.getAddress())
                .region(request.getRegion())
                .category(category)
                .build();
    }

    public static StoreResponseDTO.JoinResultDto toJoinResultDto(Store store) {
        return StoreResponseDTO.JoinResultDto.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 리뷰 생성
    public static Review toReview(StoreRequestDTO.ReviewDto request, Store store, User user, Mission mission) {
        return Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .store(store)
                .user(user)
                .mission(mission)
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDto toCreateReviewResultDto(Review review) {
        return StoreResponseDTO.CreateReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 미션 생성
    public static Mission toMission(StoreRequestDTO.MissionDto request, Store store) {
        return Mission.builder()
                .missionTitle(request.getMissionTitle())
                .missionCondition(request.getMissionCondition())
                .rewardPoint(request.getRewardPoint())
                .startDate(LocalDateTime.now()) // 시작일은 현재
                .endDate(request.getDeadline().atStartOfDay()) // 마감일
                .store(store)
                .build();
    }

    public static StoreResponseDTO.CreateMissionResultDto toCreateMissionResultDto(Mission mission) {
        return StoreResponseDTO.CreateMissionResultDto.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 미션 도전 (UserMission 생성)
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.NOT_STARTED)
                .authCode("RANDOM_CODE") // 엔티티에 not null이라 임시 값 주입
                .build();
    }

    public static StoreResponseDTO.CreateUserMissionResultDto toCreateUserMissionResultDto(UserMission userMission) {
        return StoreResponseDTO.CreateUserMissionResultDto.builder()
                .userMissionId(userMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Store 엔티티 -> StoreDetailDTO 변환
    public static StoreResponseDTO.StoreDetailDTO toStoreDetailDTO(Store store) {
        return StoreResponseDTO.StoreDetailDTO.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .region(store.getRegion())
                .build();
    }
}