package com.company.controller;

import com.company.dto.ContentResponseDTO;
import com.company.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}


/*
                    Controller untuk User Website
*/
