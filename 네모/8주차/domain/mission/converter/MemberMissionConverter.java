package com.example.umc9th_week5.domain.mission.converter;

import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.entity.Mission;

public class MemberMissionConverter {
    public static MemberMission toMemberMissionEntity(Member member, Mission mission){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    public static MemberMissionResDTO .MemberMissionInfo toMemberMissionDTO(MemberMission memberMission){
        return MemberMissionResDTO.MemberMissionInfo.builder()
                .member(MemberConverter.toMemberDTO(memberMission.getMember()))
                .mission(MissionConverter.toMissionDTO(memberMission.getMission()))
                .status(memberMission.getStatus())
                .build();
    }
}
