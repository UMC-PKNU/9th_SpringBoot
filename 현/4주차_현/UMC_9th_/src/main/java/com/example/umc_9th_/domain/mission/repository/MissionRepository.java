package com.example.umc_9th_.domain.mission.repository;

import com.example.umc_9th_.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 - 현재 지역에서 도전 가능한 미션 조회 + 페이징
    @Query("""
        SELECT new com.example.umc_9th_.domain.mission.entity.Mission(
            m.id, m.missionTitle, m.missionCondition, m.rewardPoint, m.store
        )
        FROM Mission m
        JOIN m.store s
        LEFT JOIN UserMission um
            ON um.mission = m
            AND um.user.id = :userId
        WHERE s.region = :region
          AND m.startDate <= CURRENT_TIMESTAMP
          AND (m.endDate IS NULL OR m.endDate >= CURRENT_TIMESTAMP)
        ORDER BY m.createdAt DESC
    """)
    Page<Mission> findAvailableMissionsByRegion(@Param("userId") Long userId, @Param("region") String region, Pageable pageable);
}
