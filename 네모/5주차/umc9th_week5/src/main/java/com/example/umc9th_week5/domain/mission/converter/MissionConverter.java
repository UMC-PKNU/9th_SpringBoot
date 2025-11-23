package com.example.umc9th_week5.domain.mission.converter;

import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.store.converter.StoreConverter;
import com.example.umc9th_week5.domain.store.entity.Store;

public class MissionConverter {
    // dto -> entity
    public static Mission toMissionEntity(MissionReqDTO.missionReqDTO dto, Store store){
        return Mission.builder()
                .store(store)
                .title(dto.title())
                .description(dto.description())
                .points(dto.points())
                .build();
    }

    //entity -> dto
    public static MissionResDTO .MissionInfo toMissionDTO(Mission mission){
        return MissionResDTO.MissionInfo.builder()
                .id(mission.getId())
                .store(StoreConverter.toStoreDTO(mission.getStore()))
                .title(mission.getTitle())
                .description(mission.getDescription())
                .points(mission.getPoints())
                .build();
    }
}
