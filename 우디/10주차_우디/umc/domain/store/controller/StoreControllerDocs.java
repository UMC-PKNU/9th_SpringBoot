package com.example.umc.domain.store.controller;

import com.example.umc.domain.store.dto.res.StoreResDto;
import com.example.umc.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface StoreControllerDocs {

    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 우디 (개발 완료)",
            description = "가게ID를 파라미터로 받아 특정 가게의 미션 목록을 모두 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<StoreResDto.StoreMissionsDto> getStoreMissions(@PathVariable Long storeId,
                                                               @RequestParam Integer page);
}
