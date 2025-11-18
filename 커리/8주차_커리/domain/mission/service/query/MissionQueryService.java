package com.example.umc_9th_spring.domain.mission.service.query;

import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MissionQueryService {
    Page<MissionResDTO.MissionInfo> getActiveMissionsByLocation(Long locationId, int page, int size);
    List<MissionResDTO.MissionInfo> getMissions();

    /* 01-02 유저의 미션 목록 조회 */
    List<MissionResDTO.MissionInfo> getUserMissions(Long userId);
}

