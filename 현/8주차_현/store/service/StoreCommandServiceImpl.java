package com.example.umc_9th_.domain.store.service;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.review.repository.ReviewRepository;
import com.example.umc_9th_.domain.store.converter.StoreConverter;
import com.example.umc_9th_.domain.store.dto.StoreRequestDTO;
import com.example.umc_9th_.domain.store.entity.FoodCategory;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.repository.FoodCategoryRepository;
import com.example.umc_9th_.domain.store.repository.StoreRepository;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.repository.UserMissionRepository;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 1번 api
    @Override
    public Store joinStore(StoreRequestDTO.JoinDto request) {
        FoodCategory category = foodCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Store store = StoreConverter.toStore(request, category);
        return storeRepository.save(store);
    }

    // 2번 api
    @Override
    public Review createReview(StoreRequestDTO.ReviewDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // 하드코딩 User (ID 1)
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Review 엔티티 조건 때문에 Mission도 조회해야 함
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        Review review = StoreConverter.toReview(request, store, user, mission);
        return reviewRepository.save(review);
    }

    @Override
    public Mission createMission(StoreRequestDTO.MissionDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Mission mission = StoreConverter.toMission(request, store);
        return missionRepository.save(mission);
    }

    @Override
    public UserMission challengeMission(StoreRequestDTO.ChallengeMissionDto request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        // 하드코딩 User (ID 1)
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserMission userMission = StoreConverter.toUserMission(user, mission);
        return userMissionRepository.save(userMission);
    }
}