package com.example.umc_9th_spring.domain.review.exception;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code){
        super(code);
    }
}
