package com.example.umc_9th_spring.domain.test.controller;


import com.example.umc_9th_spring.domain.test.converter.TestConverter;
import com.example.umc_9th_spring.domain.test.dto.res.TestResDTO;
import com.example.umc_9th_spring.domain.test.service.query.TestQueryService;
import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import com.example.umc_9th_spring.global.apiPayLoad.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor //@NonNull이나 final 키워드 붙은 필드를 자동 생성자 파라미터로 넘겨줌.
public class TestController { //지금 Test가 또 다른 도메인과 같다고 생각하면 됨. UserController, ReviewController. .

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResopnse<TestResDTO.TestSignupDto> test() throws Exception{
        //응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.CREATED;
        return ApiResopnse.onSuccess(
          code, TestConverter.toTestingDTO("testEmail", "testPWD")
        );
        /*
        원래는 Service층까지 가서, 회원가입이라면 User 엔티티 생성하고 저장하고,
        그후에 엔티티 객체를 DTO로 반환해서 받아먹고 반환해주면 됨 ㅇㅇ.
        Service에서 예외가 발생하면 (==실패하면) throw new GeneralException(GeneralErrorCode.XXX)로 던져주고
        @RestControllerAdvice에서 모든 예외를 잡아서, 도메인별 예외 -> ApiResponse.onFailure()로 통일된 실패 응답 생성함.
        */
    }

    @GetMapping("/exception")
    public ApiResopnse<TestResDTO.Exception> exception(@RequestParam Long flag){

        testQueryService.CheckFlag(flag);

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResopnse.onSuccess(code, TestConverter.toExceptionDTO("test 성공"));
    }

}
