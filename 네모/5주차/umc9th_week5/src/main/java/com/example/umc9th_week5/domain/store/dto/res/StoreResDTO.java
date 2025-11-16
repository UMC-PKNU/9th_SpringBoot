package com.example.umc9th_week5.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;

//@Getter
//@Builder
public class StoreResDTO {
    /*
    private Long storeId;
    private String name;
    private String region;

    public static StoreResDto from(Store store) {
        return StoreResDto.builder()
                .storeId(store.getId())
                .name(store.getName())
                .region(store.getRegion())
                .build();
    }
    */

    @Getter
    @Builder
    public static class storeInfo{
        private Long storeId;
        private String name;
        private String address;
    }
}
