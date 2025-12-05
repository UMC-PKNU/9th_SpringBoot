package com.example.umc9th.domain.member.dto.req;




import com.example.umc9th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


public class MemberReqDTO {

//    @Getter
//    public static class JoinDto {
//        private String nickname;
//        private String email;
//        private String phoneNumber;
//        private String gender; // "MALE", "FEMALE" 등으로 받을 예정
//        private String local;  // 주소
//    }

     //세션 기반 로그인용 DTO
    public record JoinDto(
            @NotBlank
            String nickname,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            String phoneNumber,
            @NotNull
            Gender gender,
            @NotNull
            String local
    ){ }
    // 토큰 기반 로그인용 DTO
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}