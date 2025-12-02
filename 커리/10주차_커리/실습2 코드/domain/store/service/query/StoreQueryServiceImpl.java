package com.example.umc_9th_spring.domain.store.service.query;

import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.mission.repository.MissionRepository;
import com.example.umc_9th_spring.domain.store.code.StoreErrorCode;
import com.example.umc_9th_spring.domain.store.exception.StoreException;
import com.example.umc_9th_spring.domain.store.repository.StoreRepository;
import com.example.umc_9th_spring.global.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.StoreMissionPage getStoreMissions(Long storeId, int page) {

        if (!storeRepository.existsById(storeId)) {
            throw new StoreException(StoreErrorCode.STORE_NOT_FOUND);
        }

        Page<Mission> missions = missionRepository.findByStore_Id(
                storeId,
                PageUtils.toPageableFromOneIndex(
                        page,
                        Sort.by(Sort.Direction.DESC, "createdAt")
                )
        );

        return MissionConverter.toStoreMissionPage(missions);
    }
}
