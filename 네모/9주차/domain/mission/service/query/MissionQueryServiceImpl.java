package com.example.umc9th_week5.domain.mission.service.query;

import com.example.umc9th_week5.domain.mission.converter.MissionConverter;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.repository.MissionRepository;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.global.validation.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{
    private final GlobalValidatorService globalValidatorService;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionInfoList getMissionByStore(Long storeId, int page) {
        Store store = globalValidatorService.validateExistStoreById(storeId);

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Mission> allByStore = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionListDTO(allByStore);
    }
}
