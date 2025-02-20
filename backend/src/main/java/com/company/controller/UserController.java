package com.company.controller;

import com.company.dto.ContentRequestDTO;
import com.company.dto.ContentResponseDTO;
import com.company.payload.UserProfileResponse;
import com.company.service.AuthService;
import com.company.service.ContentService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
//@RequestMapping("/api/admin") /api/user nanti diganti menjadi /api/admin, karena ini buat admin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    /**
     * Mengambil profil pengguna berdasarkan username.
     *
     * @param username Username pengguna
     * @return ResponseEntity dengan data profil pengguna
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestParam String username) {
        UserProfileResponse profile = userService.getUserProfile(username);
        return ResponseEntity.ok(profile);
    }

    /**
     * Memperbarui profil pengguna.
     *
     * @param username Username pengguna
     * @param profile Data profil yang diperbarui
     * @return ResponseEntity dengan status berhasil
     */
    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestParam String username,
                                                    @RequestBody UserProfileResponse profile) {
        // Memperbarui profil pengguna
        userService.updateUserProfile(username, profile);
        return ResponseEntity.ok("Profile updated successfully");
    }

    /**
     * Endpoint untuk menambahkan konten baru
     */
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()") // Hanya yang terautentikasi yang bisa mengakses
    public ResponseEntity<ContentResponseDTO> addContent(@RequestBody ContentRequestDTO contentRequestDTO) {
        ContentResponseDTO contentResponseDTO = contentService.addContent(contentRequestDTO);
        return new ResponseEntity<>(contentResponseDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint untuk memperbarui konten berdasarkan ID
     */
    @PutMapping("/{contentId}")
    @PreAuthorize("isAuthenticated()") // Hanya yang terautentikasi yang bisa mengakses
//    @PreAuthorize("hasRole('ADMIN')")  // Hanya admin yang bisa mengedit konten
    public ResponseEntity<ContentResponseDTO> updateContent(@PathVariable Long contentId,
                                                            @RequestBody ContentRequestDTO contentRequestDTO) {
        ContentResponseDTO contentResponseDTO = contentService.updateContent(contentId, contentRequestDTO);
        return new ResponseEntity<>(contentResponseDTO, HttpStatus.OK);
    }

    /**
     * Endpoint untuk menghapus konten berdasarkan ID
     */
    @DeleteMapping("/{contentId}")
    @PreAuthorize("isAuthenticated()") // Hanya yang terautentikasi yang bisa mengakses
    public ResponseEntity<Void> deleteContent(@PathVariable Long contentId) {
        contentService.deleteContent(contentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint untuk memublikasikan konten berdasarkan ID
     */
    @PutMapping("/{contentId}/publish")
    @PreAuthorize("isAuthenticated()") // Hanya yang terautentikasi yang bisa mengakses
    public ResponseEntity<ContentResponseDTO> publishContent(@PathVariable Long contentId) {
        ContentResponseDTO contentResponseDTO = contentService.publishContent(contentId);
        return new ResponseEntity<>(contentResponseDTO, HttpStatus.OK);
    }

    jagjag

}


