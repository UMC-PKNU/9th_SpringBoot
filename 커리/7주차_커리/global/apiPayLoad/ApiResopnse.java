package com.example.umc_9th_spring.global.apiPayLoad;

// * 클라이언트에게 전달되는 API 응답 표준 클래스 -> 성공 / 실패 응답을 한 포맷으로 통일하기 위한 구조

import com.example.umc_9th_spring.global.apiPayLoad.code.BaseErrorCode;
import com.example.umc_9th_spring.global.apiPayLoad.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"}) //JSON 직렬화 시 필드 순서 강제. (Spring은 기본적으로 객체를 JSON으로 변환할 때 필드 선언 순서를 보장하지 않음.)
public class ApiResopnse<T> { //제네릭 - 클래스 수준에서의 인스턴스 타입 선언변수

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private final T result;

    // static을 쓴 이유는 굳이 ApiResponse 객체 매번 생성하지 않도록 하기 위함
    public static <T> ApiResopnse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResopnse<>(true, code.getCode(), code.getMessage(), result);
    }

    public static <T> ApiResopnse<T> onSuccess(BaseSuccessCode code) {
        return new ApiResopnse<>(true, code.getCode(), code.getMessage(), null);
    }

    public static <T> ApiResopnse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResopnse<>(false, code.getCode(), code.getMessage(), result);
    }

    public static <T> ApiResopnse<T> onFailure(BaseErrorCode code) {
        return new ApiResopnse<>(false, code.getCode(), code.getMessage(), null);
    }

    /*
    서비스 층에서 비즈니스 로직 처리하다가 성공하면
    return ApiResponse.onSuccess(GeneralSuccessCode.OK, userResponseDto);
    실패하면 (== 에러가 뜨면)
    @RestControllerAdvice가 붙은 예외 핸들러에게 예외를 던져주면 된다.
    throw new GeneralException(GeneralErrorCode.NOT_FOUND);
     */

}
