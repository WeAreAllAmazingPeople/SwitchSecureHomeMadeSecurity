# SwitchSecure Homemade Security

## Goal
An extremely simple Security implementation for Switchfully students.
- [x] Just add the `@SecurityGuard` annotation to your REST endpoints.
- [x] Must use JWT tokens.

## How-to & example
```
@GetMapping("/example/admin")
@SecurityGuard(SecurityGuard.ApiUserRole.CUSTOMER)
public String getSecuredCustomer() {
    return "Hello, customer";
}
```

Once this annotation is in place, only user with a valid JWT token will get access.
:warning: The JWT must be signed with `GoGoSwitchfully` using the HMAC256/HS256 algorithm. `"role"` can have value `"ADMIN"` or `"CUSTOMER"`.
```
{
    "iss": "Parkshark",
    "iat": 1637070219,
    "exp": 1668606219,
    "aud": "Parkshark",
    "sub": "Christoph",
    "role": "CUSTOMER"
}
```
=>
```
GET http://localhost:8080/example/admin
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2MzcwNTQ3MzgsImV4cCI6MTY2ODU5MDczOCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoiQ2hyaXN0b3BoIiwiR2l2ZW5OYW1lIjoiSm9obm55IiwiU3VybmFtZSI6IlJvY2tldCIsIkVtYWlsIjoianJvY2tldEBleGFtcGxlLmNvbSIsIlJvbGUiOlsiTWFuYWdlciIsIkFETUlOIl19.QYrwmoMUu1ANpRn91RsceXkV3uNMPqLpTQbT0PM6fBc
```

![](postman.png)


## Setup & configuration
- add `com.auth0.java-jwt` to `pom.xml`
```
        <!-- needed for the SwitchSecure HomeMadeSecurity -->
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.18.2</version>
        </dependency>
```
- copy the `com.switchsecure` package into your project
- make sure to add `com.switchsecure` to your `@ComponentScan` or `@SpringBoot(scanBasePackages = "com.switchsecure")`
- the JWT token must be signed with `GoGoSwitchfully` using the HMAC256/HS256 algorithm. `"role"` can have value `"ADMIN"` or `"CUSTOMER"`.

## Creating JWT tokens + additional information
- http://jwtbuilder.jamiekurtz.com/
```
{
    "iss": "Parkshark",
    "iat": 1637070219,
    "exp": 1668606219,
    "aud": "Parkshark",
    "sub": "Christoph",
    "role": "CUSTOMER"
}
```
- the JWT token must be signed with `GoGoSwitchfully` using the HMAC256/HS256 algorithm.
- `"role"` can have value `"ADMIN"` or `"CUSTOMER"`.

- https://en.wikipedia.org/wiki/JSON_Web_Token
