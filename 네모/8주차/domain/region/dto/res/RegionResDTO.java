package com.example.umc9th_week5.domain.region.dto.res;

import lombok.Builder;
import lombok.Getter;

public class RegionResDTO {

    @Getter
    @Builder
    public static class RegionInfo{
        private Long id;
        private String regionName;
    }
}
