package com.example.umc.domain.food.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
