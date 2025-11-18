package com.example.umc_9th_spring.domain.alarm.exception;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;

public class AlarmException extends GeneralException {
    public AlarmException(BaseErrorCode code){
        super(code);
    }
}
