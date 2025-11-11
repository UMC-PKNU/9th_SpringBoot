package com.example.umc_9th_spring.domain.mission.service.query;

import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    Page<MissionResDTO.MissionInfo> getActiveMissionsByLocation(Long locationId, int page, int size);
}

