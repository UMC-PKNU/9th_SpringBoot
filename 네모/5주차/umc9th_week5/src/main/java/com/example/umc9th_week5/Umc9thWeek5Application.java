package com.example.umc9th_week5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Umc9thWeek5Application {

    public static void main(String[] args) {
        SpringApplication.run(Umc9thWeek5Application.class, args);
    }

}
