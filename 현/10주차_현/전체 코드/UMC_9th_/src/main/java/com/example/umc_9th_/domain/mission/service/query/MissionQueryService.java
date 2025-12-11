package com.example.umc_9th_.domain.mission.service.query;

import com.example.umc_9th_.domain.mission.dto.MissionResponse;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    MissionResponse.MissionPageDTO getAvailableMissions(Long userId, String location, Pageable pageable);
}