package com.example.umc9th_week5.domain.mission.repository;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    /*
    // 사용자의 미션 목록 조회(완료/진행중, 페이징 포함)
    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.member WHERE mm.member = :member AND mm.status = :status AND mm.id < :id")
    List<MemberMission> findByMemberAndStatusAndIdLessThan(@Param("member") Member member,
                                                           @Param("status") MissionStatus status,
                                                           @Param("id") Long id,
                                                           Pageable pageable);
     */

    // 사용자가 달성한 미션 개수
    Long countByMemberAndStatus(Member member, MissionStatus status);
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
}
