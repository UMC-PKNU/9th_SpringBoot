package com.example.umc.domain.store.controller;

import com.example.umc.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc.domain.mission.service.MissionService;
import com.example.umc.domain.store.dto.res.StoreResDto;
import com.example.umc.domain.store.service.query.StoreQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// StoreControllerDocs 구현해야함
@RequiredArgsConstructor
@RequestMapping("/stores")
@RestController
public class StoreController extends BaseEntity implements StoreControllerDocs {

//    private final MissionService missionService;
    private final StoreQueryService storeQueryService;

    @GetMapping("{storeId}/missions")
    public ApiResponse<StoreResDto.StoreMissionsDto> getStoreMissions(@PathVariable Long storeId,
                                                     @RequestParam Integer page) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_FOUND, storeQueryService.findMissions(storeId, page));
    }
}

