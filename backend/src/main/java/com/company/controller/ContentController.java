package com.company.controller;

import com.company.dto.ContentRequestDTO;
import com.company.dto.ContentResponseDTO;
import com.company.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * Endpoint untuk mendapatkan semua konten
     */
    @GetMapping("/all")
    public ResponseEntity<List<ContentResponseDTO>> getAllContents() {
        List<ContentResponseDTO> contentResponseDTOs = contentService.getAllContents();
        return new ResponseEntity<>(contentResponseDTOs, HttpStatus.OK);
    }

    /**
     * Endpoint untuk mendapatkan konten berdasarkan ID
     */
    @GetMapping("/{contentId}")
    public ResponseEntity<ContentResponseDTO> getContentById(@PathVariable Long contentId) {
        ContentResponseDTO contentResponseDTO = contentService.getContentById(contentId);
        return new ResponseEntity<>(contentResponseDTO, HttpStatus.OK);
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
     * 🔹 Endpoint untuk menghapus konten berdasarkan ID (Proteksi dengan Bearer Token)
     */
    @DeleteMapping("/{contentId}")
    @PreAuthorize("isAuthenticated()") // Hanya user yang login yang bisa delete
    public ResponseEntity<?> deleteContent(@PathVariable Long contentId,
                                           @RequestHeader("Authorization") String authHeader) {
        // 🔍 Validasi Token JWT
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        try {
            contentService.deleteContent(contentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found with id: " + contentId);
        }
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


/*
                    Controller untuk User Website
*/
