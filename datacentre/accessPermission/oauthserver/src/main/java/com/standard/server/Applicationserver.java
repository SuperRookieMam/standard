package com.standard.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.standard.**"})
public class Applicationserver {
    public static void main(String[] args) {
        SpringApplication.run(Applicationserver.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
