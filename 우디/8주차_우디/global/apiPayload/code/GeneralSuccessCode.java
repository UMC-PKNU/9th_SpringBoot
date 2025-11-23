package com.example.umc.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public enum GeneralSuccessCode implements BaseSuccessCode {

    // 일반적인 성공 응답 (HttpStatus.OK)
    OK(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),

    // 리소스가 성공적으로 생성되었을 때 (HttpStatus.CREATED)
    CREATED(HttpStatus.CREATED, "COMMON201", "요청이 성공적으로 처리되었으며, 리소스가 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
    // content도 넣어야될 것 같은데


    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message  ;
    }

    GeneralSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }



}
