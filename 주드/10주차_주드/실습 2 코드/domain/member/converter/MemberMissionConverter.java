package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {
    public static MemberMissionResDTO.CreateResultDTO toCreateResultDTO(MemberMission memberMission){
        return MemberMissionResDTO.CreateResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreated_at())
                .build();
    }

    public static MemberMissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission){
        return MemberMissionResDTO.MyMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .missionName(memberMission.getMission().getStore().getName())
                .missionContent(memberMission.getMission().getContent())
                .reward(Math.toIntExact(memberMission.getMission().getReward()))
                .createdAt(memberMission.getCreated_at().toLocalDate())
                .build();
    }

    public static MemberMissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<MemberMission> missionPage){
        List<MemberMissionResDTO.MyMissionDTO> missionDTOList = missionPage.stream()
                .map(MemberMissionConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return MemberMissionResDTO.MyMissionListDTO.builder()
                .missionList(missionDTOList)
                .listSize(missionDTOList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
