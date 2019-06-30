package com.standard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.standard.**"})
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
