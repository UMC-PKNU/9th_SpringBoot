package com.example.umc.domain.store.mapping;

import com.example.umc.domain.store.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // category로 컬럼명 바꾸는거 생각해보기
    @Enumerated(EnumType.STRING)
    private FoodCategory food;

    // 단방향 관계로 바꿈. 있다가 커밋하기
//    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY)
//    private List<MemberFood> memberFoods = new ArrayList<>();
}
