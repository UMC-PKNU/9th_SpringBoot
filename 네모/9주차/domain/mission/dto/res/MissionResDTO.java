package com.example.umc9th_week5.domain.mission.dto.res;

import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {
    @Getter
    @Builder
    public static class MissionInfo{
        private Long id;
        private StoreResDTO.StoreInfo store;
        private String title;
        private String description;
        private int points;
    }

    @Builder
    public record MissionInfoList(
            List<MissionInfo> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}
