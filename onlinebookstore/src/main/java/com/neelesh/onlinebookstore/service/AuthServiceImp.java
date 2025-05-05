package com.neelesh.onlinebookstore.service;

import com.neelesh.onlinebookstore.dto.AuthResponse;
import com.neelesh.onlinebookstore.dto.LoginRequest;
import com.neelesh.onlinebookstore.dto.RegisterRequest;
import com.neelesh.onlinebookstore.entity.User;
import com.neelesh.onlinebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if(!user.isPresent())
        {
            return new AuthResponse("User doesn't exist");
        }
        User existing_user = user.get();
        boolean passwordMatched = passwordEncoder.matches(loginRequest.getPassword(), existing_user.getPassword());
        if(!passwordMatched)
        {
            return new AuthResponse("Password is wrong");
        }
        return new AuthResponse("User successfully logged in.");
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
        userRepository.save(new_user);
        return new AuthResponse("User successfully created");
    }
}
