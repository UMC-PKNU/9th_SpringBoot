package com.example.umc.domain.store.dto.res;

import lombok.Builder;

import java.util.List;

public class StoreResDto {

    @Builder
    public record StoreMissionsDto (
            List<StoreMissionPreViewDto> missions,
            Integer size,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record StoreMissionPreViewDto (
        Long missionId,
        int moneyLowerLimit,
        int point
    ){}
}
