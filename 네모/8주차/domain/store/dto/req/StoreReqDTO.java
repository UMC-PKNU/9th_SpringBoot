package com.example.umc9th_week5.domain.store.dto.req;

import com.example.umc9th_week5.domain.region.dto.req.RegionReqDTO;
import com.example.umc9th_week5.global.dto.res.FoodCategoryResDTO;
import lombok.Builder;

public class StoreReqDTO {

    @Builder
    public record storeReqDTO(
            FoodCategoryResDTO.FoodCategoryInfo foodCategory,
            String name,
            String address,
            int code,
            RegionReqDTO.regionReqDTO region
    ) {}
    
    @Builder
    public record storeReqDTOForAddReview(
            Long id
    ){}

    @Builder
    public record storeReqDTOForAddMission(
            Long id
    ){}
}