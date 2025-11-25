package com.example.umc_9th_spring.domain.location.controller;

import com.example.umc_9th_spring.domain.store.dto.req.StoreReqDTO;
import com.example.umc_9th_spring.domain.store.dto.res.StoreResDTO;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name ="Location API", description = "지역 관련 API 모음")
public interface LocationControllerDocs {
    /* 04-01 특정 지역에 가게 추가하기 */
    @Operation(
            summary = "특정 지역에 가게 추가하기",
            description = "지역 ID를 기반으로 신규 가게 등록하는 API"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/{locationId}/stores")
    ApiResponse<StoreResDTO.StoreInfo> createStore(
            @PathVariable Long locationId,
            @RequestBody @Valid StoreReqDTO.CreateStore request
    );
}
