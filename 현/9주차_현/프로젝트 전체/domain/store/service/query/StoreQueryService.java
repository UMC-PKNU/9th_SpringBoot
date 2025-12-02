package com.example.umc_9th_.domain.store.service.query;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreQueryService {
    // 가게 상세 조회 (ID로 조회)
    Store getStoreDetail(Long storeId);

    // 가게 존재 여부 확인 (검증용)
    boolean existStore(Long storeId);

    // 특정 가게의 미션 목록 조회
    Page<Mission> getMissionsByStore(Long storeId, Pageable pageable);
}