package com.example.umc_9th_.domain.review.exception;

import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}