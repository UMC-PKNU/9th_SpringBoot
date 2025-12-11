package com.example.umc9th_week5.domain.region.exception;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;

public class RegionException extends GeneralException {
    public RegionException(BaseErrorCode code){
        super(code);
    }
}
