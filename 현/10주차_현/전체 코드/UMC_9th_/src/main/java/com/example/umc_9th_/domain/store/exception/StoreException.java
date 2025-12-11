package com.example.umc_9th_.domain.store.exception;

import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}