package com.example.umc_9th_spring.domain.mission.service.command;

import com.example.umc_9th_spring.domain.mission.code.MissionErrorCode;
import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.mission.exception.MissionException;
import com.example.umc_9th_spring.domain.mission.repository.MissionRepository;
import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import com.example.umc_9th_spring.domain.user.enums.UserMissionMapStatus;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserMissionMapRepository;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionMapRepository userMissionMapRepository;

    /* 01-04 미션 받기 */
    @Override
    public MissionResDTO.MissionInfo receiveMission(Long userId, Long missionId) {

        // 유저 및 미션 존재여부 체크
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 받은 미션인지 확인
        if (userMissionMapRepository.existsByUserIdAndMissionId(userId, missionId)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_RECEIVED);
        }

        // 저장
        UserMissionMap map = UserMissionMap.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionMapStatus.in_progress)
                .receivedAt(LocalDateTime.now())
                .build();

        userMissionMapRepository.save(map);

        return MissionConverter.toMissionInfoDTO(map);
    }

    /* 01-03 미션 성공 처리 */
    @Override
    public MissionResDTO.MissionInfo completeMission(Long userId, Long missionId) {

        UserMissionMap map = userMissionMapRepository
                .findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_RECEIVED));

        map.complete();

        return MissionConverter.toMissionInfoDTO(map);
    }
}
