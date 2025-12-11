package com.example.umc_9th_.domain.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    protected LocalDateTime updatedAt = LocalDateTime.now();
}
