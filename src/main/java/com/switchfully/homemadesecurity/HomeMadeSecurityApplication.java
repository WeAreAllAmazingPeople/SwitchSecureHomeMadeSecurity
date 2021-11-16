package com.switchfully.homemadesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.switchsecure")
public class HomeMadeSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeMadeSecurityApplication.class, args);
    }

}
