package com.example.umc9th_week5.domain.mission.dto.req;

import lombok.Builder;

public class MissionReqDTO {
    @Builder
    public record missionReqDTO(
        Long storeId,
        String title,
        String description,
        int points
    ){}

    @Builder
    public record missionReqDTOByStore(

    ){}

}
