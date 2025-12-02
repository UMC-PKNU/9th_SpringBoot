package com.example.umc_9th_spring.domain.store.exception;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code){
        super(code);
    }
}
