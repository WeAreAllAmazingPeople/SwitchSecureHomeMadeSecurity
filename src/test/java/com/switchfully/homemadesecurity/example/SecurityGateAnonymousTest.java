package com.switchfully.homemadesecurity.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest // <-- There is no need for a full-blown SpringBootTest
//@AutoConfigureMockMvc // <-- This one goes with @SpringBootTest
@WebMvcTest // <-- The @WebMvcTest-slice is sufficient
        // https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html
class SecurityGateAnonymousTest {
    public static final String ADMIN_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQYXJrc2hhcmsiLCJpYXQiOjE2MzcwNzAyMTksImV4cCI6MTY2ODYwNjIxOSwiYXVkIjoiUGFya3NoYXJrIiwic3ViIjoiQ2hyaXN0b3BoIiwicm9sZSI6IkFETUlOIn0.o0txm1vXKQJpVEERGdd3irOoVsi5SY0AxKMK-_ynEik";
    public static final String CUSTOMER_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQYXJrc2hhcmsiLCJpYXQiOjE2MzcwNzAyMTksImV4cCI6MTY2ODYwNjIxOSwiYXVkIjoiUGFya3NoYXJrIiwic3ViIjoiQ2hyaXN0b3BoIiwicm9sZSI6IkNVU1RPTUVSIn0.83pCNEQ5uHrYcLI66cl4dsdL8t77CE5ZFj7gIev0pW8";

    @Autowired
    private MockMvc mockMvc;


    @Test
    void anonymousTestWithoutJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK, anonymous"));
        // Then
    }


    // -------
    @Test
    void customerUrlWithCustomerJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example/customer")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + CUSTOMER_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi, Christoph"));
    }

    @Test
    void customerUrlWithoutJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example/customer"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void customerUrlWithAdminJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example/customer")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi, Christoph"));
    }
    // -------

    @Test
    void adminUrlWithAdminJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example/admin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + ADMIN_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("OK, admin"));
    }

    @Test
    void adminUrlWithoutJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminUrlWithCustomerJWT() throws Exception {
        // Given
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
//                .setControllerAdvice()
//                .addInterceptors(new SecurityGate(new UserSecurityInformation()))
//                .build();

        // When
        mockMvc.perform(get("/example/admin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + CUSTOMER_TOKEN))
                .andExpect(status().isUnauthorized());
    }
}
