package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.entity.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMissionResDto.CreateResultDto toCreateResultDto(MemberMission memberMission) {
        return MemberMissionResDto.CreateResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
