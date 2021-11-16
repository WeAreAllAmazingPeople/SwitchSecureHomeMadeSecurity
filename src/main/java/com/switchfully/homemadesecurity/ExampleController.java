package com.switchfully.homemadesecurity;

import com.switchsecure.SecurityGuard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {
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
        return "OK, customer";
    }
}
