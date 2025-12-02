package com.example.umc_9th_.domain.user.service.query;

import com.example.umc_9th_.domain.user.converter.UserConverter;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.enums.MissionStatus;
import com.example.umc_9th_.domain.user.exception.UserException;
import com.example.umc_9th_.domain.user.exception.code.UserErrorCode;
import com.example.umc_9th_.domain.user.repository.UserMissionRepository;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository; // ✅ 추가 주입

    // 1. 마이페이지 정보 조회
    @Override
    public UserResponse.MyPageInfoDTO getMyPageInfo(Long userId) {
        User user = userRepository.findUserMyPageInfo(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        return UserConverter.toMyPageInfoDTO(user);
    }

    // 2. 홈 화면 미션 달성도
    @Override
    public UserResponse.MissionSummaryDTO getMissionSummary(String location, Long userId) {

        // 유저 확인 (포인트 조회용)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 진행 중인 미션 개수
        Integer inProgress = userMissionRepository.countByUserIdAndStatus(userId, MissionStatus.PROCESSING);

        // 완료된 미션 개수
        Integer completed = userMissionRepository.countByUserIdAndStatus(userId, MissionStatus.COMPLETED);

        return new UserResponse.MissionSummaryDTO(
                inProgress + completed, // 전체 도전 수 (진행 + 완료)
                completed,
                inProgress,
                user.getUserPoint()
        );
    }

    // 3. [추가] 진행 중인 내 미션 목록 조회 (API 5번용)
    @Override
    public Page<UserMission> getUserMissions(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        return userMissionRepository.findUserMissionsWithPaging(userId, pageable);
    }
}