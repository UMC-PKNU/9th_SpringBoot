package com.example.umc9th_week5.domain.region.dto.req;

import lombok.Builder;

public class RegionReqDTO {

    @Builder
    public record regionReqDTO(
            Long id,
            String regionName
    ){}
}
