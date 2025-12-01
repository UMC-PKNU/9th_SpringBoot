package com.example.umc.domain.member.converter;

import com.example.umc.domain.member.dto.res.MemberMissionResDto;
import com.example.umc.domain.member.dto.res.MemberResDto;
import com.example.umc.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    // [1단계] MemberMission 객체 하나를 DTO로 변환하는 메서드 (내부적으로 사용)
    public static MemberMissionResDto.MemberMissionDto toMemberMissionDto(MemberMission memberMission) {
        return MemberMissionResDto.MemberMissionDto.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus().toString()) // ENUM이라면 toString()
                .moneyLowerLimit(memberMission.getMission().getMoneyLowerLimit()) // 예시 필드명
                .point(memberMission.getMission().getPoint())
                .storeName(memberMission.getMission().getStore().getName())
                .build();
    }

    // [2단계] Page 객체를 받아서 응답 DTO로 변환하는 메서드 (Controller/Service에서 호출)
    public static MemberMissionResDto.GetMemberMissionsDto toGetMemberMissionsDto(Page<MemberMission> result) {

        // stream()을 이용해서 리스트 안에 있는 MemberMission들을 하나씩 DTO로 바꿈
        List<MemberMissionResDto.MemberMissionDto> missionList = result.stream()
                .map(MemberMissionConverter::toMemberMissionDto) // 위의 메서드 호출
                .collect(Collectors.toList());

        return MemberMissionResDto.GetMemberMissionsDto.builder()
                .missionList(missionList)       // 변환된 리스트 넣기
                .listSize(missionList.size())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

//
//    public static MemberResDto.GetMemberMissions toGetMemberMissionDto(Page<MemberMission> result) {
//        return MemberResDto.GetMemberMissions.builder()
//                .status(result.)
//                .moneyLowerLimit((long) memberMission.getMission().getMoneyLowerLimit())
//                .point((long) memberMission.getMission().getPoint())
//                .storeName(memberMission.getMission().getStore().getName())
//                .build();
//    }

    public static MemberMissionResDto.CreateResultDto toCreateResultDto(MemberMission memberMission) {
        return MemberMissionResDto.CreateResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
