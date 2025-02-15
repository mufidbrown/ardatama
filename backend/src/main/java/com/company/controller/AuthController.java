package com.company.controller;

import com.company.payload.ForgotPasswordRequest;
import com.company.payload.ResetPasswordRequest;
import com.company.payload.SigninRequest;
import com.company.payload.SignupRequest;
import com.company.service.AuthService;
import com.company.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody SigninRequest request) {
        String token = authService.signin(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signout")
    public ResponseEntity<String> signout(@RequestHeader("Authorization") String token) {
        authService.signout(token.substring(7)); // Remove "Bearer " prefix
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request);
        return ResponseEntity.ok("Password reset email sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok("Password reset successfully");
    }

    /* @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            // Memanggil service untuk forgot password dan mengirimkan email reset password
            authService.forgotPassword(request);
            return ResponseEntity.ok("Password reset email sent");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending reset password email");
        }
    }*/
}
