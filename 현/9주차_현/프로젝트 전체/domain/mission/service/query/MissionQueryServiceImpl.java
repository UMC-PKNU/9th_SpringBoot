package com.example.umc_9th_.domain.mission.service.query;

import com.example.umc_9th_.domain.mission.converter.MissionConverter;
import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public MissionResponse.MissionPageDTO getAvailableMissions(Long userId, String location, Pageable pageable) {
        Page<Mission> missionPage = missionRepository.findAvailableMissionsByRegion(userId, location, pageable);
        return MissionConverter.toMissionPageDTO(missionPage);
    }
}