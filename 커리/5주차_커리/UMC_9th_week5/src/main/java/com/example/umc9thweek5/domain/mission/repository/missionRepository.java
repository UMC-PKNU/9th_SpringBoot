package com.example.umc9thweek5.domain.mission.repository;

import com.example.umc9thweek5.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface missionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        JOIN FETCH s.location l
        WHERE l.id = :locationId
          AND m.deadline > :now
        ORDER BY m.createdAt DESC
        """,
            countQuery = """
        SELECT COUNT(m)
        FROM Mission m
        JOIN m.store s
        JOIN s.location l
        WHERE l.id = :locationId
          AND m.deadline > :now
        """)
    Page<Mission> findActiveMissionsByLocation(
            @Param("locationId") Long locationId,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );
}


/*
SELECT *
FROM mission m
JOIN store s ON m.store_id = s.id
JOIN location l ON s.location_id = l.id
WHERE m.deadline > '2025-10-31T15:43:00'
ORDER BY m.created_at DESC;

-> JPA가 @ManyToOne, @OneToMany로 연관관계 매핑된 걸 알고 있어서 매핑된 것끼리 조인 조건을 생성해줌.

Mission 클래스 안에는 아래와 같은 코드가 존재함.
# @ManyToOne(fetch = FetchType.LAZY)
# @JoinColumn(name = "store_id")
# private Store store;

이 정보 덕분에 JOIN store s ON m.store_id = s.id 조인 조건을 바로 생성해줌.

 */