package com.example.umc9th_week5.domain.mission.converter;

import com.example.umc9th_week5.domain.member.converter.MemberConverter;
import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.MemberMission;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {
    public static MemberMission toMemberMissionEntity(Member member, Mission mission){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();
    }

    public static MemberMissionResDTO .MemberMissionInfo toMemberMissionDTO(MemberMission memberMission){
        return MemberMissionResDTO.MemberMissionInfo.builder()
                .member(MemberConverter.toMemberDTO(memberMission.getMember()))
                .mission(MissionConverter.toMissionDTO(memberMission.getMission()))
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionInfoList toMemberMissionListDTO(Page<MemberMission> memberMission){
        return MemberMissionResDTO.MemberMissionInfoList.builder()
                .memberMissionList(memberMission.getContent().stream()
                        .map(MemberMissionConverter::toMemberMissionDTO)
                        .toList())
                .isFirst(memberMission.isFirst())
                .isLast(memberMission.isLast())
                .listSize(memberMission.getSize())
                .totalElements(memberMission.getTotalElements())
                .totalPage(memberMission.getTotalPages())
                .build();
    }
}
