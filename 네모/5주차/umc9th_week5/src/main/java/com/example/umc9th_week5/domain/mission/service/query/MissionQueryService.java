package com.example.umc9th_week5.domain.mission.service.query;

import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionInfoList getMissionByStore(Long storeId, int page);
}
