package com.example.umc_9th_.domain.store.entity;

import com.example.umc_9th_.domain.base.entity.BaseEntity;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "store")
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private FoodCategory category;

    @Column(nullable = false, length = 100)
    private String storeName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 50)
    private String region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missions;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
