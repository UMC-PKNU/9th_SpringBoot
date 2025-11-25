package com.example.umc9th_week5.global.dto.res;


import com.example.umc9th_week5.global.enums.FoodCategoryName;
import lombok.Builder;
import lombok.Getter;

public class FoodCategoryResDTO {

    @Getter
    @Builder
    public static class FoodCategoryInfo{
        private Long id;
        private FoodCategoryName categoryName;
    }
}
