package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<Mission> getMissionsByStore(Long storeId, Integer page) {
        // 1. 가게 확인 (존재하지 않으면 예외 발생)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다."));

        // 2. 페이징 설정 (page는 0부터 시작하므로 -1)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. 조회 및 반환
        return missionRepository.findAllByStore(store, pageRequest);
    }
}
