package com.example.umc_9th_spring.domain.store.repository;

import com.example.umc_9th_spring.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
