package com.example.umc_9th_.domain.store.service.command;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.store.converter.StoreConverter;
import com.example.umc_9th_.domain.store.dto.StoreRequest;
import com.example.umc_9th_.domain.store.entity.FoodCategory;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.exception.StoreException;
import com.example.umc_9th_.domain.store.exception.code.StoreErrorCode;
import com.example.umc_9th_.domain.store.repository.FoodCategoryRepository;
import com.example.umc_9th_.domain.store.repository.StoreRepository;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.enums.MissionStatus;
import com.example.umc_9th_.domain.user.repository.UserMissionRepository;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public Store joinStore(StoreRequest.JoinDTO request) {
        FoodCategory category = foodCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Store store = StoreConverter.toStore(request, category);
        return storeRepository.save(store);
    }

    @Override
    public Review createReview(StoreRequest.ReviewDTO request) {
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        User user = userRepository.findById(1L) // 임시 하드코딩
                .orElseThrow(() -> new RuntimeException("User not found"));

        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        Review review = StoreConverter.toReview(request, store, user, mission);
        return reviewRepository.save(review);
    }

    @Override
    public Mission createMission(StoreRequest.MissionDTO request) {
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = StoreConverter.toMission(request, store);
        return missionRepository.save(mission);
    }

    @Override
    public UserMission challengeMission(StoreRequest.ChallengeMissionDTO request) {
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        User user = userRepository.findById(1L) // 임시 하드코딩
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserMission userMission = StoreConverter.toUserMission(user, mission);
        return userMissionRepository.save(userMission);
    }

    // 진행 중인 미션 -> 완료로 변경 [추가]
    @Override
    public UserMission completeMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        if (userMission.getStatus() != MissionStatus.PROCESSING) {
            throw new RuntimeException("진행 중인 미션만 완료할 수 있습니다.");
        }

        userMission.complete();

        return userMission; // 변경된 상태 리턴 (JPA Dirty Checking으로 자동 저장)
    }
}