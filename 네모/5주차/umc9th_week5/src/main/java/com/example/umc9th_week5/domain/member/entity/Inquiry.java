package com.example.umc9th_week5.domain.member.entity;

import com.example.umc9th_week5.domain.member.enums.InquiryCategory;
import com.example.umc9th_week5.domain.member.enums.InquiryStatus;
import com.example.umc9th_week5.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="inquiry")
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="category", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InquiryCategory category = InquiryCategory.ETC;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InquiryStatus status = InquiryStatus.PENDING;
}
