package com.example.umc.domain.store.service.query;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.repository.MissionRepository;
import com.example.umc.domain.store.converter.StoreConverter;
import com.example.umc.domain.store.dto.res.StoreResDto;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.exception.StoreException;
import com.example.umc.domain.store.exception.code.StoreErrorCode;
import com.example.umc.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public StoreResDto.StoreMissionsDto findMissions(Long storeId, Integer page) {

        Store store = storeRepository.findById(Math.toIntExact(storeId))
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.searchMission(storeId, pageRequest);

        return StoreConverter.toStoreMissionsDto(result);
    }
}
