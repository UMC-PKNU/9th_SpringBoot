package com.example.umc9th_week4.global.entity;

import com.example.umc9th_week4.domain.member.entity.MemberFoodCategory;
import com.example.umc9th_week4.domain.store.entity.Store;
import com.example.umc9th_week4.global.enums.FoodCategoryName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="food_category")
public class FoodCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="foodCategory", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();

    @OneToMany(mappedBy = "foodCategory", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberFoodCategory> memberFoodCategoryList = new ArrayList<>();

    @Column(name="name", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodCategoryName name;
}
