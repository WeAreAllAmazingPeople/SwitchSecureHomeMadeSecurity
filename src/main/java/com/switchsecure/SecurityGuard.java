package com.switchsecure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(security = { @SecurityRequirement(name = "bearer-key") })
public @interface SecurityGuard {
    ApiUserRole value() ;

    enum ApiUserRole {
        ADMIN, CUSTOMER
    }
}
