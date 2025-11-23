package com.example.umc.domain.test.controller;

import com.example.umc.domain.test.converter.TestConverter;
import com.example.umc.domain.test.dto.res.TestResponseDto;
import com.example.umc.domain.test.exception.TestException;
import com.example.umc.domain.test.service.query.TestQueryService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResponseDto.Testing> test() throws Exception {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

//        throw new TestException(GeneralErrorCode.INTERNAL_SERVER_ERROR);
        return ApiResponse.onSuccess(code, TestConverter.toTestingDto("This is Test!"));
    }

    // 예외상황
    @GetMapping("/exception")
    public ApiResponse<TestResponseDto.Exception> exception(@RequestParam Long flag) {

        testQueryService.checkFlag(flag);

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDto("This is Test!"));
    }
}
