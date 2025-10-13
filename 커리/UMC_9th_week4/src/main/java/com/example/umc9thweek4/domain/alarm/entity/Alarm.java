package com.example.umc9thweek4.domain.alarm.entity;

import com.example.umc9thweek4.domain.alarm.enums.AlarmType;
import com.example.umc9thweek4.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Lob //이 필드를 DB의 CLOB 또는 BLOB 타입으로 매핑해줌.
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "which_type", nullable = false, length = 20)
    private AlarmType whichType;
}
