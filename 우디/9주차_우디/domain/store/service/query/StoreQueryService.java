package com.example.umc.domain.store.service.query;

import com.example.umc.domain.store.dto.res.StoreResDto;

public interface StoreQueryService {
    StoreResDto.StoreMissionsDto findMissions(Long storeId, Integer page);
}
