package com.example.umc_9th_spring.global.apiPayLoad.handler;


import com.example.umc_9th_spring.global.apiPayLoad.ApiResopnse;
import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.code.GeneralErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    //그냥 throw new TestException 하면 에러 응답도 통일되어 처리되지 않음. 

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResopnse<Void>> handleException(
            GeneralException ex
    ){
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResopnse.onFailure(ex.getCode()));
    }
    //GeneralException에서 최상단에서 BaseErrorCode를 계속 예외가 나면서 초기화되는 거임.
    //도메인 별 UserException이나 ReivewException이 extends GeneralException해서 자바는 다형성이 되니까 업캐스팅해서 들어와짐.



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResopnse<String>> handleException(
        Exception ex
    ){
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResopnse.onFailure(
                   code,
                   ex.getMessage()
                ));

    }

    
}
