package com.example.umc_9th_.domain.store.service.query;

import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.mission.repository.MissionRepository;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.exception.StoreException;
import com.example.umc_9th_.domain.store.exception.code.StoreErrorCode;
import com.example.umc_9th_.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public Store getStoreDetail(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
    }

    @Override
    public boolean existStore(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    // 특정 가게의 미션 목록 조회 [추가]
    @Override
    public Page<Mission> getMissionsByStore(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        return missionRepository.findAllByStore(store, pageable);
    }
}