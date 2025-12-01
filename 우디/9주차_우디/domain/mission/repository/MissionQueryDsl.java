package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryDsl {
    Page<Mission> searchMission(Long storeId, Pageable pageable);
}
