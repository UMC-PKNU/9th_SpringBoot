package com.example.umc_9th_spring.domain.store.repository;

import com.example.umc_9th_spring.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("""
        SELECT s
        FROM Store s
        JOIN FETCH s.location l
        WHERE s.id = :storeId
    """)
    Optional<Store> findWithLocationById(@Param("storeId") Long storeId);

}
