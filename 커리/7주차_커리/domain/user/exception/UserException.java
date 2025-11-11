package com.example.umc_9th_spring.domain.user.exception;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;

public class UserException extends GeneralException {
    public UserException(BaseErrorCode code){
        super(code);
    }
}
