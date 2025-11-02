package com.example.umc9th_week5.domain.mission.repository;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 사용자의 미션 목록 조회(완료/진행중, 페이징 포함)
    List<MemberMission> findByMemberAndStatusAndIdLessThan(Member member, MissionStatus missionStatus, Long id, Pageable pageable);

    // 사용자가 선택한 주소에 따른 진행중 미션 목록 조회(페이징 포함)
    Page<MemberMission> findByMemberAndMissionStoreAddressContainingAndIdLessThanAndStatus(
            Member member,
            String addrKeyword,
            Long id,
            MissionStatus status,
            Pageable pageable
    );

    // 사용자가 달성한 미션 개수
    Long countByMemberAndStatus(Member member, MissionStatus status);
}
