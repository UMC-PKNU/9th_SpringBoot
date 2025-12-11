package com.example.umc_9th_.domain.user.repository;

import com.example.umc_9th_.domain.user.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
}