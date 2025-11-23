package com.example.umc.global.apiPayload.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode errorCode;

    /*
    public GeneralException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseErrorCode getErrorCode() {
        return errorCode;
    }
     */
}
