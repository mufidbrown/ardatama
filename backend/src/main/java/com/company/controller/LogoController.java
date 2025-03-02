package com.company.controller;


import com.company.dto.ContentRequestDTO;
import com.company.dto.ContentResponseDTO;
import com.company.dto.LogoResponseDTO;
import com.company.entity.LogoEntity;
import com.company.service.LogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/api/logo")
public class LogoController {

    private final LogoService logoService;

    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }

    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LogoResponseDTO> uploadLogo(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(logoService.uploadLogo(file));
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<LogoResponseDTO>> getLogo() {
        return ResponseEntity.ok(logoService.getLogo());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteLogo() {
        logoService.deleteLogo();
        return ResponseEntity.noContent().build();
    }

    // Endpoint untuk upload logo
//    @PostMapping("/upload")
//    @PreAuthorize("isAuthenticated()") // Memastikan hanya user yang terautentikasi yang bisa meng-upload logo
//    public ResponseEntity<LogoResponseDTO> uploadLogo(@RequestParam("file") MultipartFile file) throws IOException {
//        LogoResponseDTO logoResponse = logoService.uploadLogo(file);  // Mengirim file ke logoService untuk diproses
//        return ResponseEntity.ok(logoResponse);  // Mengembalikan response dengan data logo
//    }
//
//    // Endpoint untuk mendapatkan logo
//    @GetMapping
//    public ResponseEntity<Optional<LogoResponseDTO>> getLogo() {
//        Optional<LogoResponseDTO> logoResponse = logoService.getLogo();  // Mendapatkan logo terakhir dari logoService
//        return ResponseEntity.ok(logoResponse);  // Mengembalikan response dengan data logo
//    }
}



