package com.example.umc9th_week5.global.converter;

import com.example.umc9th_week5.global.dto.res.FoodCategoryResDTO;
import com.example.umc9th_week5.global.entity.FoodCategory;

public class FoodCategoryConverter {
    //dto->entity
    public static FoodCategory toFoodCategoryEntity(FoodCategoryResDTO.FoodCategoryInfo dto){
        return FoodCategory.builder()
                .id(dto.getId())
                .name(dto.getCategoryName())
                .build();
    }
}
