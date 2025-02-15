package com.company.service;

import com.company.entity.User;
import com.company.payload.ForgotPasswordRequest;
import com.company.payload.ResetPasswordRequest;
import com.company.payload.SigninRequest;
import com.company.payload.SignupRequest;
import com.company.repository.UserRepository;
import com.company.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    private final Map<String, String> resetTokenStore = new HashMap<>();

    @Override
    public void signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        userRepository.save(user);
    }

    @Override
    public String signin(SigninRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }

    @Override
    public void signout(String token) {
        jwtUtil.invalidateToken(token);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        String token = jwtUtil.generatePasswordResetToken(user.getUsername());
        resetTokenStore.put(token, user.getUsername());

        emailService.sendPasswordResetEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        String token = request.getToken();
        String username = resetTokenStore.get(token);

        // Cek apakah username null atau token tidak valid
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid or expired reset token");
        }

        // Memeriksa apakah token valid atau tidak
        String tokenValidationResult = jwtUtil.validatePasswordResetToken(token);
        if (tokenValidationResult == null || tokenValidationResult.isEmpty()) {
            throw new IllegalArgumentException("Token validation failed");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Memperbarui password pengguna
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        // Menghapus token reset yang sudah digunakan
        resetTokenStore.remove(token);
    }


   /* @Override
    public void resetPassword(ResetPasswordRequest request) {
        String username = resetTokenStore.get(request.getToken());
        if (username == null || !jwtUtil.validatePasswordResetToken(request.getToken())) {
            throw new IllegalArgumentException("Invalid or expired reset token");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        resetTokenStore.remove(request.getToken());
    }*/
}

