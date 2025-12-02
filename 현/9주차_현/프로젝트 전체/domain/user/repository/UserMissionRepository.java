package com.example.umc_9th_.domain.user.repository;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 기존 페이징 쿼리 유지...
    @Query("""
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        WHERE um.user.id = :userId
          AND um.status IN (
            com.example.umc_9th_.domain.user.enums.MissionStatus.PROCESSING,
            com.example.umc_9th_.domain.user.enums.MissionStatus.COMPLETED
          )
        ORDER BY
          CASE WHEN um.completedAt IS NULL THEN 1 ELSE 0 END,
          um.completedAt DESC
       """)
    Page<UserMission> findUserMissionsWithPaging(@Param("userId") Long userId, Pageable pageable);

    Integer countByUserIdAndStatus(Long userId, MissionStatus status);

    // 이미 도전 중인지 확인하는 메서드
    boolean existsByUserAndMissionAndStatus(User user, Mission mission, MissionStatus status);

    // 유저와 미션 ID로 진행 중인 미션 찾기
    Optional<UserMission> findByUserAndMissionAndStatus(User user, Mission mission, MissionStatus status);
}