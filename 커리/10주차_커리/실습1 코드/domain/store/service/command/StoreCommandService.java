package com.example.umc_9th_spring.domain.store.service.command;

import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.store.dto.req.StoreReqDTO;
import com.example.umc_9th_spring.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    MissionResDTO.MissionInfo createMissionForStore(Long storeId, MissionReqDTO.CreateMission request);

    StoreResDTO.StoreInfo createStore(Long locationId, StoreReqDTO.CreateStore request);
}
