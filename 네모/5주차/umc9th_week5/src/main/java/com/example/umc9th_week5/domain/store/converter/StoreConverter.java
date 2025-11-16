package com.example.umc9th_week5.domain.store.converter;

import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_week5.domain.store.entity.Store;

public class StoreConverter {
    public static StoreResDTO.storeInfo toStoreDTO(Store store){
        return StoreResDTO.storeInfo.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}
