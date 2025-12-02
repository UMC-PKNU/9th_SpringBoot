// StoreDto.java

package com.example.umc9th.domain.store.dto;

import com.example.umc9th.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreDto {
    private final Long id;
    private final String name;
    private final String located_at;

    @Builder
    public StoreDto(Long id, String name, String locatedAt) {
        this.id = id;
        this.name = name;
        this.located_at = locatedAt;
    }
    public static StoreDto from(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .locatedAt(store.getLocated_at())
                .build();
    }
}