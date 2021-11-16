package com.switchsecure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityGate implements HandlerInterceptor {
    public static final String JWT_SECRET = "GoGoSwitchfully";
    Logger logger = LoggerFactory.getLogger(SecurityGate.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        com.switchsecure.SecurityGuard annotation = ((HandlerMethod) handler).getMethod().getAnnotation(com.switchsecure.SecurityGuard.class);

        if (annotation == null) {
            return true;
        }
        SecurityGuard.ApiUserRole role = annotation.value();
        if (role == null) {
            return true; // normally, this is not possible, ... but hey
        } // SECURITY REQUIRED BELOW

        logger.info("Only " + role + " can access this REST endpoint");


        // if JWT does NOT contain the correct role
        if(jwtMatchSecurityGuardRole(role, request)) {
            return true;
        } else {
            response.sendError(401);
            return false;
        }

    }

    private boolean jwtMatchSecurityGuardRole(com.switchsecure.SecurityGuard.ApiUserRole value, HttpServletRequest request) {
        if(getJWT(request) == null) {
            return false;
        }
        try {
            if (!isValidToken(getJWT(request))) {
                return false;
            }
        } catch (Exception e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
        return value.name().equals(getRoleFromJwtToken(getJWT(request)))
               || com.switchsecure.SecurityGuard.ApiUserRole.ADMIN.name().equals(getRoleFromJwtToken(getJWT(request))) ;


    }

    private String getJWT(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

    private boolean isValidToken(String authToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(authToken);

            return true;
        } catch (Exception e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

//    private String getUserNameFromJwtToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(JWT_SECRET)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }

    private String getRoleFromJwtToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("role").asString();
    }


}
