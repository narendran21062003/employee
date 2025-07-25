package com.example.Employeedetails.controller;

import com.example.Employeedetails.dto.AuthResponse;
import com.example.Employeedetails.dto.LoginDto;
import com.example.Employeedetails.dto.RegisterDto;
import com.example.Employeedetails.security.JwtTokenProvider;
import com.example.Employeedetails.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.generateAccessToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        if (tokenProvider.validateToken(refreshToken)) {
            String email = tokenProvider.getUsernameFromToken(refreshToken);

            // Create authentication without credentials
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

            String newAccessToken = tokenProvider.generateAccessToken(authentication);
            String newRefreshToken = tokenProvider.generateRefreshToken(authentication);

            return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}