package com.example.umc_9th_spring.domain.store.service.query;

import com.example.umc_9th_spring.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;

public interface StoreQueryService {

    MissionResDTO.StoreMissionPage getStoreMissions(Long storeId, int page);
}
