package com.example.umc9th_week5.domain.mission.dto.req;

import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import lombok.Builder;

public class MissionReqDTO {
    @Builder
    public record missionReqDTO(
        StoreReqDTO.storeReqDTOForAddMission store,
        String title,
        String description,
        int points
    ){}

    @Builder
    public record missionReqDTOForChallenge(
            Long id
    ){}

}
