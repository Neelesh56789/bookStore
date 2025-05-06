package com.neelesh.onlinebookstore.controller;

import com.neelesh.onlinebookstore.dto.AuthResponse;
import com.neelesh.onlinebookstore.dto.LoginRequest;
import com.neelesh.onlinebookstore.dto.RegisterRequest;
import com.neelesh.onlinebookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public  AuthResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
