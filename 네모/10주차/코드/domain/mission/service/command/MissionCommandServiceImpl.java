package com.example.umc9th_week5.domain.mission.service.command;

import com.example.umc9th_week5.domain.mission.converter.MissionConverter;
import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.repository.MissionRepository;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.global.validator.GlobalValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final GlobalValidatorService globalValidatorService;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionInfo addMission(MissionReqDTO.missionReqDTO dto) {
        Store store = globalValidatorService.validateExistStoreById(dto.storeId());
        Mission mission = MissionConverter.toMissionEntity(dto, store);

        missionRepository.save(mission);
        return MissionConverter.toMissionDTO(mission);
    }

}
