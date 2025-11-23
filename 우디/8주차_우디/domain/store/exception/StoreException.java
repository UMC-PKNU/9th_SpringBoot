package com.example.umc.domain.store.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}