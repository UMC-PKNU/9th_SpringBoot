package com.example.umc.domain.store.entity;

import com.example.umc.domain.store.enums.StoreStatus;
import com.example.umc.domain.food.entity.Food;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=20)
    private String name;

    @Column(nullable = false, length=50)
    private String address;

    @Column(nullable = false, length=5)
    private String position;

    @Column(nullable = false)
    private double rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus status;

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
//    private List<Mission> missions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
