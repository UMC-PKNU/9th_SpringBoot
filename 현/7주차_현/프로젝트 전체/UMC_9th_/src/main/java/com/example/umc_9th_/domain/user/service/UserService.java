package com.example.umc_9th_.domain.user.service;

import com.example.umc_9th_.domain.user.converter.UserConverter;
import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 1. 회원가입 및 선호 카테고리 등록

    @Transactional
    public UserResponse.JoinResultDTO joinUser(UserRequest.JoinDTO request) {
        User newUser = UserConverter.toUser(request);

        return UserConverter.toJoinResultDTO(newUser);
    }

    // 2. 사용자 위치 (동) 수정 (PUT /api/me/location)

    @Transactional
    public UserResponse.LocationUpdateDTO updateLocation(Long userId, UserRequest.UpdateLocationDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return UserResponse.LocationUpdateDTO.builder()
                .userId(userId)
                .newLocation(request.getAddress())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
    }

    // 3. 마이페이지 기본 정보 조회 (GET /api/me/mypage)

    public UserResponse.MyPageInfoDTO getMyPageInfo(Long userId) {
        User user = userRepository.findUserMyPageInfo(userId)
                .orElseThrow(() -> new IllegalArgumentException("User info not found"));

        return UserConverter.toMyPageInfoDTO(user);
    }

    // 4. 홈 화면 미션 달성도 요약 조회 (GET /api/home/mission-summary)

    public UserResponse.MissionSummaryDTO getMissionSummary(String location, Long userId) {
        // 1. UserMission 정보를 기반으로 요약 통계 계산

        return UserResponse.MissionSummaryDTO.builder()
                .totalMissionCount(10)
                .completedMissionCount(7)
                .currentPoint(15000)
                .build();
    }
}