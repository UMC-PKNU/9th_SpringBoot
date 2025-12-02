package com.example.umc9th.domain.member.dto.req;


import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class JoinDto {
        private String nickname;
        private String email;
        private String phoneNumber;
        private String gender; // "MALE", "FEMALE" 등으로 받을 예정
        private String local;  // 주소
    }

}