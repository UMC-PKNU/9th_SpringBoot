package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.mapping.MemberMission;
import com.example.umc.domain.member.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberMissionQueryDsl {

    // 9주차 미션3
    Page<MemberMission> findMemberMissions(Long memberId, MissionStatus status, Pageable pageable);
}
