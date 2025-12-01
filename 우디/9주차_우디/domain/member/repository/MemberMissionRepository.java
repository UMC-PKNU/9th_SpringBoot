package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long>, MemberMissionQueryDsl  {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus missionStatus);

    // 미션3 리펙토링
    // 미션 -> 스토어 단방향 매핑
    // 페이징을 하려면 전체 페이지가 몇 개인지 알아야 되는데
    // FETCH JOIN을 사용하면 개수 세는 쿼리가 꼬일 수 있어서
    // 카운트 쿼리를 따로 작성했다.
    @Query(value = "SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission mi " +
            "JOIN FETCH mi.store s " +
            "WHERE mm.member.id = :memberId AND mm.status = :status " +
            "ORDER BY mm.id DESC",
            countQuery = "SELECT count(mm) FROM MemberMission mm " +
                        "WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findMemberMissionByMemberIdAndStatus(@Param("memberId") Long memberId, @Param("status") MissionStatus missionStatus, Pageable pageable);
}
