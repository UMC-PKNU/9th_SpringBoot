package com.example.umc_9th_spring.domain.location.controller;

import com.example.umc_9th_spring.domain.store.code.StoreSuccessCode;
import com.example.umc_9th_spring.domain.store.dto.req.StoreReqDTO;
import com.example.umc_9th_spring.domain.store.dto.res.StoreResDTO;
import com.example.umc_9th_spring.domain.store.service.command.StoreCommandService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController implements LocationControllerDocs{

    private final StoreCommandService storeCommandService;

    /* 04-01 특정 지역에 가게 추가하기 */
    // Ctrl + Alt + Shift + T -> Pull memebers up 하면 인터페이스에 올려줌.
    @PostMapping("/{locationId}/stores")
    @Override
    public ApiResponse<StoreResDTO.StoreInfo> createStore(
            @PathVariable Long locationId,
            @RequestBody @Valid StoreReqDTO.CreateStore request
    ) {
        StoreResDTO.StoreInfo result =
                storeCommandService.createStore(locationId, request);
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_REGISTER_SUCCESS, result);
    }
}
