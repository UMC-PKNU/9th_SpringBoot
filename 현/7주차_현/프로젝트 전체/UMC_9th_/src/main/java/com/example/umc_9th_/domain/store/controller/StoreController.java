package com.example.umc_9th_.domain.store.controller;

import com.example.umc_9th_.domain.store.dto.StoreResponse; // DTO 정의 예정
import com.example.umc_9th_.domain.store.service.StoreService;
import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    // GET /api/stores/{storeId}
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponse.StoreDetailDTO> getStoreDetail(@PathVariable Long storeId) {

        // StoreResponse.StoreDetailDTO response = storeService.getStoreDetail(storeId);

        // 임시 응답 데이터 반환
        StoreResponse.StoreDetailDTO response = StoreResponse.StoreDetailDTO.builder()
                .storeId(storeId)
                .storeName("임시 가게 이름")
                .address("임시 주소")
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}