package com.company.controller;

import com.company.dto.ContentRequestDTO;
import com.company.dto.ContentResponseDTO;
import com.company.payload.UserProfileResponse;
import com.company.payload.UserProfileUpdateRequest;
import com.company.service.AuthService;
import com.company.service.ContentService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
     * @return ResponseEntity dengan data profil pengguna
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(Authentication authentication) {
        String username = authentication.getName(); // Ambil username dari token JWT
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

    /**
     * Endpoint untuk menambahkan konten baru
     */
//    @PostMapping("/create")
//    @PreAuthorize("isAuthenticated()") // Hanya yang terautentikasi yang bisa mengakses
//    public ResponseEntity<ContentResponseDTO> addContent(@RequestBody ContentRequestDTO contentRequestDTO) {
//        ContentResponseDTO contentResponseDTO = contentService.addContent(contentRequestDTO);
//        return new ResponseEntity<>(contentResponseDTO, HttpStatus.CREATED);
//    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()") // Hanya yang login bisa mengakses
    public ResponseEntity<ContentResponseDTO> addContent(@RequestBody ContentRequestDTO contentRequestDTO) {
        ContentResponseDTO contentResponseDTO = contentService.addContent(contentRequestDTO);
        return new ResponseEntity<>(contentResponseDTO, HttpStatus.CREATED);
    }


    /**
     * Endpoint untuk memperbarui konten berdasarkan ID
     */
//    @PutMapping("/{contentId}")
//    @PreAuthorize("isAuthenticated()") // Hanya yang terautentikasi yang bisa mengakses
//    public ResponseEntity<ContentResponseDTO> updateContent(@PathVariable Long contentId,
//                                                            @RequestBody ContentRequestDTO contentRequestDTO) {
//        ContentResponseDTO contentResponseDTO = contentService.updateContent(contentId, contentRequestDTO);
//        return new ResponseEntity<>(contentResponseDTO, HttpStatus.OK);
//    }
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()") // Hanya user yang terautentikasi yang bisa update
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @RequestBody UserProfileUpdateRequest updateRequest,
            @AuthenticationPrincipal UserDetails userDetails) {

        UserProfileResponse updatedProfile = userService.updateUserProfile(userDetails.getUsername(), updateRequest);
        return ResponseEntity.ok(updatedProfile);
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

}


