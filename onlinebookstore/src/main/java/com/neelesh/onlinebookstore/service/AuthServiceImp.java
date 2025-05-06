package com.neelesh.onlinebookstore.service;

import com.neelesh.onlinebookstore.dto.AuthResponse;
import com.neelesh.onlinebookstore.dto.LoginRequest;
import com.neelesh.onlinebookstore.dto.RegisterRequest;
import com.neelesh.onlinebookstore.entity.User;
import com.neelesh.onlinebookstore.jwt.JwtService;
import com.neelesh.onlinebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest) {
        // 1. Authenticate using AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        // 4. Return the token
        return new AuthResponse(token);
    }


    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        Optional<User> user = userRepository.findByEmail(registerRequest.getEmail());
        if(user.isPresent())
        {
            return new AuthResponse("User already exists");
        }
        User new_user = new User();
        new_user.setEmail(registerRequest.getEmail());
        new_user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        new_user.setUsername(registerRequest.getUsername());
        new_user.setRole(registerRequest.getRole());
        userRepository.save(new_user);
        return new AuthResponse("User successfully created");
    }
}
