package com.example.umc_9th_.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

public class StoreResponse {

    @Getter
    @Builder
    public static class StoreDetailDTO {
        private Long storeId;
        private String storeName;
        private String address;
        private String region;
    }
}