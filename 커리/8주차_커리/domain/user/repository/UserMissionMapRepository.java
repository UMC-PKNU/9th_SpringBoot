package com.example.umc_9th_spring.domain.user.repository;

import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import com.example.umc_9th_spring.domain.user.enums.UserMissionMapStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMissionMapRepository extends JpaRepository<UserMissionMap, Long> {

    List<UserMissionMap> findByUserIdOrderByReceviedAtDesc(Long userId);
    Optional<UserMissionMap> findByUserIdAndMissionId(Long userId, Long missionId);
    boolean existsByUserIdAndMissionId(Long userId, Long missionId);
    List<UserMissionMap> findAllByUserId(Long userId);

    //진행 중, 진행 완료 -> umm.status에서 파라미터로 받아 조회하는 쿼리 + Pagable 객체 받아서 정보 나눠 가져오기까지
    @Query(value = """
        SELECT umm
        FROM UserMissionMap umm
        JOIN FETCH umm.mission m
        JOIN FETCH m.store s
        WHERE umm.user.id = :userId
          AND umm.status = :status
        ORDER BY umm.receivedAt DESC
        """,
            countQuery = """
        SELECT COUNT(umm)
        FROM UserMissionMap umm
        WHERE umm.user.id = :userId
          AND umm.status = :status
        """)
    Page<UserMissionMap> findUserMissionsByStatus(
            @Param("userId") Long userId,
            @Param("status") UserMissionMapStatus status,
            Pageable pageable
    );
}
