package com.example.umc_9th_spring.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class StoreReqDTO {

    @Getter
    @Builder
    public static class CreateStore {

        @NotBlank
        private String name;

        @NotBlank
        private String address;

    }
}
