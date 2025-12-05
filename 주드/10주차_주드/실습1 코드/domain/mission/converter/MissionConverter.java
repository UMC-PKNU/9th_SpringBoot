package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResDTO.MissionResPreViewDTO toMissionResPreViewDTO(Mission mission) {
        return MissionResDTO.MissionResPreViewDTO.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .name(mission.getName())
                .content(mission.getContent())
                .local(mission.getLocal())
                .build();
    }

    public static MissionResDTO.MissionResListDTO toMissionResListDTO(Page<Mission> missionPage) {

        // Stream을 사용하여 리스트 변환
        List<MissionResDTO.MissionResPreViewDTO> missionPreViewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionResPreViewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionResListDTO.builder()
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
