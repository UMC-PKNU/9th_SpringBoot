package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>{

    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findAllByMemberIdAndStatus(@Param("memberId") Long memberId, @Param("status") MemberMissionStatus status, Pageable pageable);
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

    // 이미 도전 중인지 확인 (상태가 'CHALLENGING'이거나 'COMPLETE'인 게 있는지)
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MemberMissionStatus status);

    // 혹은 상태 상관없이 이미 기록이 있는지 확인 (재도전 불가 정책이라면)
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
