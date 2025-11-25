package com.example.umc9th_week5.domain.region.entity;

import com.example.umc9th_week5.domain.store.entity.Store;
import com.example.umc9th_week5.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="region")
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="region_name", nullable = false)
    private String regionName;

    @OneToMany(mappedBy="region", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();
}
