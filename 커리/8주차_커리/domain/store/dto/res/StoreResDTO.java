package com.example.umc_9th_spring.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {

    @Getter
    @Builder
    public static class StoreInfo {
        private Long id;
        private String name;
        private String address;
        private String locationName;
    }
}
