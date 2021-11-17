package com.switchfully.homemadesecurity.example;

import com.switchsecure.SecurityGuard;
import com.switchsecure.UserSecurityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {
    @Autowired
    @Qualifier("getUsernameAndRoleFromJWT")
    UserSecurityInformation user;

    @GetMapping
    public String getAnonymous() {
        return "OK, anonymous";
    }

    @GetMapping("admin")
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public String getSecuredAdmin() {
        return "OK, admin";
    }

    @GetMapping("customer")
    @SecurityGuard(SecurityGuard.ApiUserRole.CUSTOMER)
    public String getSecuredCustomer() {
        return "Hi, " + user.getName();
    }
}
