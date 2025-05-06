package com.neelesh.onlinebookstore.dto;

public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getMessage() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
