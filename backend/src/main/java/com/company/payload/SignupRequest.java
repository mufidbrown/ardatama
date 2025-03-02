package com.company.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private String fullName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getters and Setters
}

