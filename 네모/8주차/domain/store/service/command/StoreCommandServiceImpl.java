package com.example.umc9th_week5.domain.store.service.command;

import com.example.umc9th_week5.domain.region.code.RegionErrorCode;
import com.example.umc9th_week5.domain.region.converter.RegionConverter;
import com.example.umc9th_week5.domain.region.entity.Region;
import com.example.umc9th_week5.domain.region.repository.RegionRepository;
import com.example.umc9th_week5.domain.store.converter.StoreConverter;
import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.domain.store.repository.StoreRepository;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public StoreResDTO.StoreInfo addStore(StoreReqDTO.storeReqDTO dto){
        Region region = regionRepository.findById(dto.region().id()).orElseThrow(() -> new GeneralException(RegionErrorCode.NOT_FOUND));

        Store store = StoreConverter.toStoreEntity(dto, region);

        storeRepository.save(store);
        return StoreConverter.toStoreDTO(store);
    }

}
