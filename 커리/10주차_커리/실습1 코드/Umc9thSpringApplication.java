package com.example.umc_9th_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc9thSpringApplication {

    public static void main(String[] args) {
        System.out.println(">>> DB_USER = " + System.getenv("DB_USER"));
        SpringApplication.run(Umc9thSpringApplication.class, args);
    }
}
