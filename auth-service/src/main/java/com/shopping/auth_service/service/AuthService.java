package com.shopping.auth_service.service;


import com.shopping.auth_service.dto.AuthResponse;
import com.shopping.auth_service.dto.LoginRequest;
import com.shopping.auth_service.exception.InvalidCredentialsException;
import com.shopping.auth_service.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    // users hardcoded
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public AuthResponse login(LoginRequest request) {
        // vali credentials
        if (!ADMIN_USER.equals(request.getUsername()) || !ADMIN_PASSWORD.equals(request.getPassword())) {
           throw new InvalidCredentialsException("Invalid username or password");
        }

        // get token
        String token = jwtTokenProvider.generateToken(request.getUsername());

        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .expiresIn(jwtTokenProvider.getJwtExpiration())
                .username(request.getUsername())
                .build();
    }

    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}