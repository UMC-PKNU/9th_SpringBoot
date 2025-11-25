package com.example.umc_9th_spring.global.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

    private static final int PAGE_SIZE = 10;

    public static Pageable toPageableFromOneIndex(int page, Sort sort) {

        int pgIndex = page - 1;

        if(pgIndex < 0){
            pgIndex = 0;
        }

        return PageRequest.of(pgIndex, PAGE_SIZE, sort);

    }
}
