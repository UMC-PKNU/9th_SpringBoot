package com.example.umc9th_week5.domain.mission.repository;

import com.example.umc9th_week5.domain.mission.entity.Mission;
import com.example.umc9th_week5.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 사용자가 선택한 주소에 따른 미션 목록 조회(페이징 포함)
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.address LIKE %:addrKeyword% AND m.id < :id")
    Page<Mission> findByStoreAndAddressContainingAndIdLessThan(
            @Param("store") Store store,
            @Param("addrKeyword") String addrKeyword,
            @Param("id") Long id,
            Pageable pageable
    );
}
