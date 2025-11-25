package com.example.umc9th_week5.domain.mission.dto.res;

import com.example.umc9th_week5.domain.member.dto.res.MemberResDTO;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberMissionResDTO {
    @Getter
    @Builder
    public static class MemberMissionInfo{
        private MemberResDTO.MemberInfo member;
        private MissionResDTO.MissionInfo mission;
        private MissionStatus status;
    }

    @Builder
    public record MemberMissionInfoList(
            List<MemberMissionInfo> memberMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}
