package com.example.umc.domain.member.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
