package com.standard.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = {"com.standard.**"})
@Configuration
public class TestApplicatoon {
    public static void main(String[] args) {
        SpringApplication.run(TestApplicatoon.class, args);
    }
}
