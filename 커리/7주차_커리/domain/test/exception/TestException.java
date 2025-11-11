package com.example.umc_9th_spring.domain.test.exception;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;

//도메인별 Exception
public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code); //부모 클래서 생성자 호출 -> BaseErrorCode가 초기화됨.
    }
}
