package com.company.controller;

import com.company.dto.ContentResponseDTO;
import com.company.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "contentId", required = false) Long contentId) {
        String response = contentService.uploadImage(file, contentId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/image/{contentId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long contentId) {
        byte[] imageData = contentService.getImage(contentId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Sesuaikan dengan format gambar
                .body(imageData);
    }
}


/*
                    Controller untuk User Website
*/
