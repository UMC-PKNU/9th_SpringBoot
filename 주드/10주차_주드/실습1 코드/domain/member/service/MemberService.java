package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberService {
    Member joinMember(MemberReqDTO.JoinDto request);
    Member findMember(Long userId);
    List<MemberMission> findMyProgressMissions(Long userId);
    List<MemberMission> findMyCompleteMissions(Long userId);
    Integer getCompletedMissionCount(Long userId);
    MemberMission challengeMission(MemberMissionReqDTO.MissionAcceptDto request, Long userId);
    Page<MemberMission> findMyProgressMissions(Long userId, Integer page);
}
