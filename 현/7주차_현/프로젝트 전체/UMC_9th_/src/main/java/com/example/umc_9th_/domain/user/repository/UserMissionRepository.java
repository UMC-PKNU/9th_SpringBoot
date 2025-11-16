package com.example.umc_9th_.domain.user.repository;

import com.example.umc_9th_.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // JPQL로 진행중 + 완료된 미션 조회 (페이징)
    @Query("""
        SELECT new com.example.umc_9th_.domain.user.entity.UserMission(
            um.id, um.mission, um.status, um.startedAt,  um.completedAt
        )
        FROM UserMission um
        WHERE um.user.id = :userId
          AND um.status IN (
            com.example.umc_9th_.domain.user.enums.MissionStatus.PROCESSING,
            com.example.umc_9th_.domain.user.enums.MissionStatus.COMPLETED
          )
        ORDER BY
          CASE WHEN um.completedAt IS NULL THEN 1 ELSE 0 END,
          um.completedAt DESC
       """) /* CASE WHEN 쓴 이유 : completedAt은 진행 중일 때 NULL이라서 진행중인 애들은
       DB종류마다 다르게 정렬이 됨 -> 그래서 NULL(진행 중)이면 1을 줘서 뒤로, 아니면 0을 줘서 앞으로 한 뒤
       completedAt DESC로 정렬한 것 -> 완료된 미션 순서대로 위로, 진행 중은 아래로(DESC니까)*/
    Page<UserMission> findUserMissionsWithPaging(@Param("userId") Long userId, Pageable pageable);

}
