package com.example.umc_9th_.domain.store.converter;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.store.dto.StoreRequest;
import com.example.umc_9th_.domain.store.dto.StoreResponse;
import com.example.umc_9th_.domain.store.entity.FoodCategory;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.enums.MissionStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    // Store 생성
    public static Store toStore(StoreRequest.JoinDTO request, FoodCategory category) {
        return Store.builder()
                .storeName(request.name()) // record는 getXxx() 아님!
                .address(request.address())
                .region(request.region())
                .category(category)
                .build();
    }

    public static StoreResponse.JoinResultDTO toJoinResultDTO(Store store) {
        return new StoreResponse.JoinResultDTO(
                store.getId(),
                LocalDateTime.now()
        );
    }

    // 리뷰 생성
    public static Review toReview(StoreRequest.ReviewDTO request, Store store, User user, Mission mission) {
        return Review.builder()
                .content(request.content())
                .rating(request.rating())
                .store(store)
                .user(user)
                .mission(mission)
                .build();
    }

    public static StoreResponse.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return new StoreResponse.CreateReviewResultDTO(
                review.getId(),
                LocalDateTime.now()
        );
    }

    // 미션 생성
    public static Mission toMission(StoreRequest.MissionDTO request, Store store) {
        return Mission.builder()
                .missionTitle(request.missionTitle())
                .missionCondition(request.missionCondition())
                .rewardPoint(request.rewardPoint())
                .startDate(LocalDateTime.now())
                .endDate(request.deadline().atStartOfDay())
                .store(store)
                .build();
    }

    public static StoreResponse.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return new StoreResponse.CreateMissionResultDTO(
                mission.getId(),
                LocalDateTime.now()
        );
    }

    // 미션 도전
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.NOT_STARTED)
                .authCode("RANDOM_CODE")
                .build();
    }

    public static StoreResponse.CreateUserMissionResultDTO toCreateUserMissionResultDTO(UserMission userMission) {
        return new StoreResponse.CreateUserMissionResultDTO(
                userMission.getId(),
                LocalDateTime.now()
        );
    }

    // 상세 조회
    public static StoreResponse.StoreDetailDTO toStoreDetailDTO(Store store) {
        return new StoreResponse.StoreDetailDTO(
                store.getId(),
                store.getStoreName(),
                store.getAddress(),
                store.getRegion()
        );
    }

    // 미션 리스트 -> DTO 변환 (Stream 사용!) [추가]
    public static StoreResponse.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {
        List<StoreResponse.MissionDTO> missionList = missionPage.stream()
                .map(StoreConverter::toMissionDTO)
                .collect(Collectors.toList());

        return new StoreResponse.MissionListDTO(
                missionList,
                missionPage.getTotalElements(),
                missionPage.getTotalPages(),
                missionPage.isFirst(),
                missionPage.isLast(),
                missionPage.getNumber() + 1
        );
    }

    // 개별 미션 변환 [추가]
    public static StoreResponse.MissionDTO toMissionDTO(Mission mission) {
        return new StoreResponse.MissionDTO(
                mission.getId(),
                mission.getMissionCondition(),
                mission.getRewardPoint(),
                mission.getStore().getStoreName()
        );
    }
}