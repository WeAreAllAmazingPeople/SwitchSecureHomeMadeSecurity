package com.switchsecure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class VerySecureSecurityConfig implements WebMvcConfigurer {
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html

    @Autowired
    SecurityGate theBestSecurityEVER;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(theBestSecurityEVER);
    }
}
