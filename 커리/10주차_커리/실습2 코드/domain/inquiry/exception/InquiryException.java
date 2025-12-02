package com.example.umc_9th_spring.domain.inquiry.exception;

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;

public class InquiryException extends GeneralException {
    public InquiryException(BaseErrorCode code){
        super(code);
    }
}
