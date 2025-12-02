package com.example.umc_9th_.domain.test.exception;

import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}