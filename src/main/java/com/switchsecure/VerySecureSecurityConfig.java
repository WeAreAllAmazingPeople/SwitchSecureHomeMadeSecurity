package com.switchsecure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
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

    @Bean
    @RequestScope
    public UserSecurityInformation getUsernameAndRoleFromJWT() {
        return new UserSecurityInformation();
    }

    @Bean
    public OpenAPI addJWTAuthorizationHeaderToSwagger() {
        https://github.com/springdoc/springdoc-openapi/issues/49
        return new OpenAPI().components(new Components().addSecuritySchemes("bearer-key",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
