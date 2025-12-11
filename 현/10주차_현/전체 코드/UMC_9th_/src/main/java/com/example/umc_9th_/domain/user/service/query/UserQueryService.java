package com.example.umc_9th_.domain.user.service.query;

import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryService {
    UserResponse.MyPageInfoDTO getMyPageInfo(Long userId);
    UserResponse.MissionSummaryDTO getMissionSummary(String location, Long userId);
    Page<UserMission> getUserMissions(Long userId, Pageable pageable);
    UserResponse.LoginDTO login(UserRequest.LoginDTO request);
}