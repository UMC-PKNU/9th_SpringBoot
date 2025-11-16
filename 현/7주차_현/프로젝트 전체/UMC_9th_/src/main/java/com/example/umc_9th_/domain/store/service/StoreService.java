package com.example.umc_9th_.domain.store.service;

import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    // ID를 사용하여 상점 엔티티를 조회하는 메서드

    public Store findStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElse(null);
    }

    // 상점 상세 정보를 조회하는 API를 위한 메서드
    public Store getStoreDetail(Long storeId) {
        return findStoreById(storeId);
    }
}