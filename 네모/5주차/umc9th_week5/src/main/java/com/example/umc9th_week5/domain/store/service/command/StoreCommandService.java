package com.example.umc9th_week5.domain.store.service.command;

import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.StoreInfo addStore(StoreReqDTO.storeReqDTO dto);
}
