package com.example.umc_9th_spring.domain.test.service.query;

import com.example.umc_9th_spring.domain.test.exception.TestException;
import com.example.umc_9th_spring.domain.test.code.TestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//GET요청 전용 Service 클래스.
@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void CheckFlag(Long flag){
        if (flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
