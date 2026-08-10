package com.forgeos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@Modulithic
@SpringBootApplication
public class ForgeOSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForgeOSApplication.class, args);
    }
}
