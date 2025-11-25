package com.example.umc_9th_spring.domain.store.converter;

import com.example.umc_9th_spring.domain.store.dto.req.StoreReqDTO;
import com.example.umc_9th_spring.domain.store.dto.res.StoreResDTO;
import com.example.umc_9th_spring.domain.store.entity.Location;
import com.example.umc_9th_spring.domain.store.entity.Store;

public class StoreConverter {

    public static Store toEntity(StoreReqDTO.CreateStore dto, Location location) {
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .location(location)
                .build();
    }

    public static StoreResDTO.StoreInfo toStoreInfoDTO(Store store) {
        return StoreResDTO.StoreInfo.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .locationName(store.getLocation().getName())
                .build();
    }
}
