package com.example.umc_9th_spring.global.apiPayLoad.handler;


import com.example.umc_9th_spring.global.apiPayLoad.ApiResponse;
import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.code.GeneralErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.exception.GeneralException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GeneralExceptionAdvice {
    //그냥 throw new TestException 하면 에러 응답도 통일되어 처리되지 않음. 

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ){
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(ex.getCode()));
    }
    //GeneralException에서 최상단에서 BaseErrorCode를 계속 예외가 나면서 초기화되는 거임.
    //도메인 별 UserException이나 ReivewException이 extends GeneralException해서 자바는 다형성이 되니까 업캐스팅해서 들어와짐.


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
        Exception ex
    ){
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                   code,
                   ex.getMessage()
                ));

    }
    // 1) @RequestBody DTO 검증 실패 (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>(); //ApiResponse - T result에 Map 형식으로 결과 담기 위함임.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.REQUEST_BODY_VALIDATION_FAILED;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // 2) ConstraintViolationException은 메서드 파라미터 검증 실패를 잡음 (@RequestParam, @PathVariable, @Validated, @커스텀 검증 어노테이션)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        GeneralErrorCode errorCode = GeneralErrorCode.REQUEST_PARAM_VALIDATION_FAILED;

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {

            String msg = violation.getMessage();
            String path = violation.getPropertyPath().toString();

            // 1) @ValidPage 검증 실패
            if (msg.equals("VALID_PAGE")) {
                errorCode = GeneralErrorCode.PAGE_VALIDATION_FAILED;
                errors.put("page", "page는 1 이상이어야 합니다.");
                continue;
            }

            // 2) ExistUsers 검증 실패 (@ExistUsers)
            if (msg.equals("존재하지 않는 사용자입니다.")) {
                errorCode = GeneralErrorCode.USER_NOT_FOUND_VALIDATION_FAILED;
                errors.put("userId", "존재하지 않는 사용자입니다.");
                continue;
            }

            // 3) PathVariable 검증 실패 추정 로직
            boolean isPathVariable =
                    violation.getExecutableParameters().length == 0 &&
                            violation.getExecutableReturnValue() == null;

            if (isPathVariable) {
                errorCode = GeneralErrorCode.PATH_VARIABLE_VALIDATION_FAILED;
            } else {
                errorCode = GeneralErrorCode.REQUEST_PARAM_VALIDATION_FAILED;
            }

            errors.put(path, msg);
        }

        ApiResponse<Map<String, String>> errorResponse =
                ApiResponse.onFailure(errorCode, errors);

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }


    // 3) 폼 데이터 바인딩 실패 (@ModelAttribute)
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleBindException(
            BindException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.FORM_BINDING_FAILED;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // 4) 타입 불일치 (예: /users/abc → Long userId)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleTypeMismatchException(
            MethodArgumentTypeMismatchException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = ex.getName();
        String requiredType = ex.getRequiredType() != null
                ? ex.getRequiredType().getSimpleName()
                : "알 수 없음";

        errors.put(fieldName, String.format("'%s' 파라미터는 타입 %s 이어야 합니다.", fieldName, requiredType));

        GeneralErrorCode code = GeneralErrorCode.TYPE_MISMATCH;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // 5) JSON 파싱 실패 (잘못된 요청 본문)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleJsonParseException(
            HttpMessageNotReadableException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        errors.put("requestBody", "요청 본문(JSON)을 읽을 수 없습니다.");

        GeneralErrorCode code = GeneralErrorCode.JSON_PARSE_ERROR;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

}
