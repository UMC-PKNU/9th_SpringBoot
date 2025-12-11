package com.example.umc9th_week5.domain.review.exception;

import com.example.umc9th_week5.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code){
        super(code);
    }
}
