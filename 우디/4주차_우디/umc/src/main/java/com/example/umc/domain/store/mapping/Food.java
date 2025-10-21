package com.example.umc.domain.store.mapping;

import com.example.umc.domain.member.entity.mapping.MemberFood;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FoodCategory food;

    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY)
    private List<MemberFood> memberFoods = new ArrayList<>();
}
