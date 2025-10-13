package com.example.umc9th_week4.domain.store.entity;

import com.example.umc9th_week4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="region")
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="region", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();

    @Column(name="name", nullable = false)
    private String name;
}
