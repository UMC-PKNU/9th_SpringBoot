package com.example.umc9th_week5.domain.mission.service.command;

import com.example.umc9th_week5.domain.mission.converter.MissionConverter;
import com.example.umc9th_week5.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th_week5.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.mission.repository.MissionRepository;
import com.example.umc9th_week5.domain.store.code.StoreErrorCode;
import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.domain.store.repository.StoreRepository;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionInfo addMission(MissionReqDTO.missionReqDTO dto) {
        Store store = storeRepository.findById(dto.store().id()).orElseThrow(() -> new GeneralException(StoreErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMissionEntity(dto, store);

        missionRepository.save(mission);
        return MissionConverter.toMissionDTO(mission);
    }
}
