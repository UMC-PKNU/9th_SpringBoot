package com.example.umc9th_week5.domain.store.service.command;

import com.example.umc9th_week5.domain.region.entity.Region;
import com.example.umc9th_week5.domain.store.converter.StoreConverter;
import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.domain.store.repository.StoreRepository;
import com.example.umc9th_week5.global.entity.FoodCategory;
import com.example.umc9th_week5.global.validation.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{
    private final GlobalValidatorService globalValidatorService;
    private final StoreRepository storeRepository;

    @Override
    public StoreResDTO.StoreInfo addStore(StoreReqDTO.storeReqDTO dto){
        Region region = globalValidatorService.validateExistRegionById(dto.regionId());
        FoodCategory foodCategory = globalValidatorService.validateExistFoodCategoryById(dto.foodCategoryId());

        Store store = StoreConverter.toStoreEntity(dto, region, foodCategory);

        storeRepository.save(store);
        return StoreConverter.toStoreDTO(store);
    }

}
