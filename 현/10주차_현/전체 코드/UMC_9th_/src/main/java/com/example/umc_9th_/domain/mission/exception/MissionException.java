package com.example.umc_9th_.domain.mission.exception;

import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}