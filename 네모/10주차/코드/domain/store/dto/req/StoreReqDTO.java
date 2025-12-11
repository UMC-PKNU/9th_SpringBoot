package com.example.umc9th_week5.domain.store.dto.req;

import lombok.Builder;

public class StoreReqDTO {

    @Builder
    public record storeReqDTO(
            Long foodCategoryId,
            String name,
            String address,
            int code,
            Long regionId
    ) {}
}