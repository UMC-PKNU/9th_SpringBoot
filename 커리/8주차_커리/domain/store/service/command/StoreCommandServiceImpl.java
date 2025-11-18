package com.example.umc_9th_spring.domain.store.service.command;

import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.mission.repository.MissionRepository;
import com.example.umc_9th_spring.domain.store.code.StoreErrorCode;
import com.example.umc_9th_spring.domain.store.converter.StoreConverter;
import com.example.umc_9th_spring.domain.store.dto.req.StoreReqDTO;
import com.example.umc_9th_spring.domain.store.dto.res.StoreResDTO;
import com.example.umc_9th_spring.domain.store.entity.Location;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.domain.store.exception.StoreException;
import com.example.umc_9th_spring.domain.store.repository.LocationRepository;
import com.example.umc_9th_spring.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {


    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionInfo createMissionForStore(Long storeId, MissionReqDTO.CreateMission request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toEntity(request, store);
        missionRepository.save(mission);

        return MissionConverter.toMissionInfoDTO(mission);
    }

    @Override
    public StoreResDTO.StoreInfo createStore(Long locationId, StoreReqDTO.CreateStore request) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCATION_NOT_FOUND));

        Store store = StoreConverter.toEntity(request, location);
        storeRepository.save(store);

        return StoreConverter.toStoreInfoDTO(store);
    }

}
