package com.example.umc_9th_.global.apiPayload.exception;

import com.example.umc_9th_.global.apiPayload.ApiResponse;
import com.example.umc_9th_.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th_.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> validation(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .findFirst()
                .orElse("유효성 검사 실패");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure(GeneralErrorCode.VALID_FAIL, errorMessage));
    }
}
