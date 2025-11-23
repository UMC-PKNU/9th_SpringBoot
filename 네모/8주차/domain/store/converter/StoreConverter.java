package com.example.umc9th_week5.domain.store.converter;

import com.example.umc9th_week5.domain.region.converter.RegionConverter;
import com.example.umc9th_week5.domain.region.dto.req.RegionReqDTO;
import com.example.umc9th_week5.domain.region.entity.Region;
import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.global.converter.FoodCategoryConverter;

public class StoreConverter {
    //entity -> dto
    public static StoreResDTO.StoreInfo toStoreDTO(Store store){
        return StoreResDTO.StoreInfo.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .region(RegionConverter.toRegionResDTO(store.getRegion()))
                .build();
    }

    //dto -> entity
    public static Store toStoreEntity(StoreReqDTO.storeReqDTO dto, Region region){
        return Store.builder()
                .foodCategory(FoodCategoryConverter.toFoodCategoryEntity(dto.foodCategory()))
                .address(dto.address())
                .code(dto.code())
                .name(dto.name())
                .region(region)
                .build();
    }

    public static Store toStoreEntity(StoreReqDTO.storeReqDTO dto){
        return Store.builder()
                .foodCategory(FoodCategoryConverter.toFoodCategoryEntity(dto.foodCategory()))
                .address(dto.address())
                .code(dto.code())
                .name(dto.name())
                .region(RegionConverter.toRegionEntity(dto.region()))
                .build();
    }
}
