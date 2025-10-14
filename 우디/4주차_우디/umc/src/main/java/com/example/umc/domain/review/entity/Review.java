package com.example.umc.domain.review.entity;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.review.ReviewStatus;
import com.example.umc.domain.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable=false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable=false)
    private LocalDateTime modifiedAt;

    @Column(nullable=false)
    private double rating;

    @Column(nullable=false)
    private String content;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private ReviewStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
//    private List<ReviewComment> comments = new ArrayList<>();
}
