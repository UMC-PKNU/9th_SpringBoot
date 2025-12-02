package com.example.umc_9th_.domain.mission.service.command;

import com.example.umc_9th_.domain.mission.dto.MissionRequest;
import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.exception.MissionException;
import com.example.umc_9th_.domain.mission.exception.code.MissionErrorCode;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
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
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    // 1. 미션 도전하기 (중복 검사 포함)
    @Override
    public MissionResponse.MissionStartDTO startMission(Long missionId, Long userId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 이미 도전 중인지 검사
        if (userMissionRepository.existsByUserAndMissionAndStatus(user, mission, MissionStatus.PROCESSING)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.PROCESSING)
                .startedAt(LocalDateTime.now())
                .authCode("1234") // 실제로는 가게에서 발급한 코드여야 함 (여기선 고정)
                .build();

        userMissionRepository.save(userMission);

        return new MissionResponse.MissionStartDTO(
                missionId,
                "PROCESSING",
                userMission.getStartedAt()
        );
    }

    // 2. 미션 성공 인증하기 (포인트 지급 포함)
    @Override
    public MissionResponse.MissionSuccessResultDTO successMission(Long missionId, Long userId, MissionRequest.SuccessMissionDTO request) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 사용자가 도전 중인(PROCESSING) 해당 미션을 찾음
        UserMission userMission = userMissionRepository.findByUserAndMissionAndStatus(user, mission, MissionStatus.PROCESSING)
                .orElseThrow(() -> new RuntimeException("도전 중인 미션이 아닙니다."));

        // 인증 코드 확인 (여기선 "1234"가 정답이라고 가정)
        if (!"1234".equals(request.authCode())) {
            throw new RuntimeException("인증 코드가 일치하지 않습니다.");
        }

        // 1. 미션 완료 처리 (UserMission 엔티티의 메서드 사용)
        userMission.complete();

        // 2. 포인트 지급 (User 엔티티의 메서드 사용)
        user.addPoint(mission.getRewardPoint());

        // 3. 응답 반환
        return new MissionResponse.MissionSuccessResultDTO(
                missionId,
                "COMPLETED",
                mission.getRewardPoint(),
                0L // 리뷰 ID는 나중에 리뷰 작성 시 생성되므로 0 또는 null
        );
    }
}