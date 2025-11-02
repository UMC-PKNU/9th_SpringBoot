package com.example.umc9th_week5.domain.store.entity;

import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.review.entity.StoreReview;
import com.example.umc9th_week5.global.entity.BaseEntity;
import com.example.umc9th_week5.global.entity.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @Builder.Default
    private List<StoreReview> storeReviewList = new ArrayList<>();

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="region_id")
    private Region region;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="category_id")
    private FoodCategory foodCategory;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="code", nullable = false, length=10)
    private int code;
}
