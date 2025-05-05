package com.neelesh.onlinebookstore.service;

import com.neelesh.onlinebookstore.dto.AuthResponse;
import com.neelesh.onlinebookstore.dto.LoginRequest;
import com.neelesh.onlinebookstore.dto.RegisterRequest;

public interface AuthService {
    public AuthResponse login(LoginRequest loginRequest);
    public AuthResponse register(RegisterRequest registerRequest);
}
