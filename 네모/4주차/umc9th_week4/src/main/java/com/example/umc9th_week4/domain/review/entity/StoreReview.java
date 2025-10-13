package com.example.umc9th_week4.domain.review.entity;

import com.example.umc9th_week4.domain.member.entity.Member;
import com.example.umc9th_week4.domain.store.entity.Store;
import com.example.umc9th_week4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="store_review")
public class StoreReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="rating", nullable = false)
    private Float rating;

    @Column(name="content", nullable = false)
    private String content;
}
