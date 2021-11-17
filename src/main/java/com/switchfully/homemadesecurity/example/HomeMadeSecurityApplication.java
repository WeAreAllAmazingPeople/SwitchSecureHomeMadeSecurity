package com.switchfully.homemadesecurity.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.switchsecure", "com.switchfully"})
public class HomeMadeSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeMadeSecurityApplication.class, args);
    }

}
