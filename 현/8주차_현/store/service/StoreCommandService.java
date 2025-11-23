package com.example.umc_9th_.domain.store.service;

import com.example.umc_9th_.domain.store.dto.StoreRequestDTO;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.user.entity.UserMission;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.JoinDto request);
    Review createReview(StoreRequestDTO.ReviewDto request);
    Mission createMission(StoreRequestDTO.MissionDto request);
    UserMission challengeMission(StoreRequestDTO.ChallengeMissionDto request);
}