package com.example.umc_9th_.domain.store.service.command;

import com.example.umc_9th_.domain.store.dto.StoreRequest;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.user.entity.UserMission;

public interface StoreCommandService {
    Store joinStore(StoreRequest.JoinDTO request);
    Review createReview(StoreRequest.ReviewDTO request);
    Mission createMission(StoreRequest.MissionDTO request);
    UserMission challengeMission(StoreRequest.ChallengeMissionDTO request);
    UserMission completeMission(Long userMissionId);
}