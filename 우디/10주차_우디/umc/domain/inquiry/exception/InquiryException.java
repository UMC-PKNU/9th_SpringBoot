package com.example.umc.domain.inquiry.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

public class InquiryException extends GeneralException {

    public InquiryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
