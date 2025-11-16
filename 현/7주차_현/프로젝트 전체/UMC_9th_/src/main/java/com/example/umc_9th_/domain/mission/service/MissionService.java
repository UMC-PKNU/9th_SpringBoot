package com.example.umc_9th_.domain.mission.service;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.dto.MissionRequest;
import com.example.umc_9th_.domain.mission.converter.MissionConverter;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    //  미션 도전 시작 처리 (UserMission 생성)

    @Transactional
    public MissionResponse.MissionStartDTO startMission(Long missionId, Long userId) {
        // 1. User, Mission 엔티티 조회 (존재 여부 확인)
        // 2. UserMission 엔티티 생성 및 저장 (상태: PROCESSING)

        // 임시 응답 데이터 반환
        return MissionResponse.MissionStartDTO.builder()
                .missionId(missionId)
                .currentStatus("PROCESSING")
                .startedAt(LocalDateTime.now())
                .build();
    }

    // 홈 화면에서 해당 지역의 도전 가능한 미션 목록 조회

    public MissionResponse.MissionPageDTO getAvailableMissions(Long userId, String location, Pageable pageable) {
        Page<Mission> missionPage = missionRepository.findAvailableMissionsByRegion(userId, location, pageable);

        // 2. Page<Mission>에서 getContent()를 사용해 List<Mission>을 추출하고 DTO로 변환합니다.
        List<MissionResponse.MissionDTO> dtoList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionDTO)
                .collect(Collectors.toList());

        // 임시 응답 데이터 반환
        return MissionResponse.MissionPageDTO.builder()
                .missionList(dtoList)
                .build();
    }

    @Transactional
    public MissionResponse.MissionSuccessResultDTO successMission(Long missionId, Long userId, MissionRequest.SuccessMissionDTO request) {
        // 1. UserMission을 조회하고 authCode, 기간 등 유효성 검증
        // 2. UserMission 상태를 COMPLETED로 변경
        // 3. User Point 업데이트
        return MissionResponse.MissionSuccessResultDTO.builder()
                .missionId(missionId)
                .finalStatus("COMPLETED")
                .earnedPoint(500)
                .reviewIdToRegister(126L)
                .build();
    }
}