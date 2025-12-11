package com.example.umc9th_week5.domain.store.controller;

import com.example.umc9th_week5.domain.store.code.StoreSuccessCode;
import com.example.umc9th_week5.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th_week5.domain.store.dto.res.StoreResDTO;
import com.example.umc9th_week5.domain.store.service.command.StoreCommandService;
import com.example.umc9th_week5.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController implements StoreControllerDocs{
    private final StoreCommandService storeCommandService;

    @PostMapping()
    public ApiResponse<StoreResDTO.StoreInfo> addStore(@RequestBody StoreReqDTO.storeReqDTO dto){

        return ApiResponse.onSuccess(StoreSuccessCode.OK, storeCommandService.addStore(dto));
    }

}