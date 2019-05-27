package com.toxin.clickerback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ClickerbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickerbackApplication.class, args);
    }
}
