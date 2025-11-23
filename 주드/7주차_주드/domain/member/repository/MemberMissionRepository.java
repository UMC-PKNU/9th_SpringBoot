package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>{
    // 1. 진행 중인 미션 조회
    @Query("SELECT mm" +
            " FROM MemberMission mm" +
            " JOIN FETCH mm.mission m" +
            " JOIN FETCH m.store s" +
            " WHERE mm.member.id = :memberId  AND mm.status = 'PROGRESS'")
    List<MemberMission> findProgressMissions(@Param("memberId") Long memberId);
    // 2. 완료한 미션 조회
    @Query("SELECT mm" +
            " FROM MemberMission mm" +
            " JOIN FETCH mm.mission m" +
            " JOIN FETCH m.store s" +
            " WHERE mm.member.id = :memberId  AND mm.status = 'COMPLETE'")
    List<MemberMission> findCompleteMissions(@Param("memberId") Long memberId);

    // 3. 완료한 미션 개수 조회 (UI의 '7/10' 계산용)

    @Query("SELECT COUNT(mm)" +
            " FROM MemberMission mm" +
            " WHERE mm.member.id = :memberId AND mm.status = 'COMPLETE'")
    Integer countCompletedMissions(@Param("memberId") Long memberId);
}
