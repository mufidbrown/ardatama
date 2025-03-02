package com.company.service;

import com.company.dto.ContentRequestDTO;
import com.company.dto.ContentResponseDTO;
import com.company.entity.Content;
import com.company.entity.User;
import com.company.exception.ResourceNotFoundException;
import com.company.repository.ContentRepository;
import com.company.repository.UserRepository;
import com.company.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public ContentResponseDTO addContent(ContentRequestDTO contentRequestDTO) {
        // Ambil user yang sedang login dari Spring Security
        String username = authenticationFacade.getAuthenticatedUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Content content = new Content();
        content.setTitle(contentRequestDTO.getTitle());
        content.setDescription(contentRequestDTO.getDescription());
        content.setContentText(contentRequestDTO.getContentText());
        content.setImageUrl(contentRequestDTO.getImageUrl());
        content.setPublished(false);
        content.setUser(user); // Set user sebelum save

        Content savedContent = contentRepository.save(content);

        return new ContentResponseDTO(
                savedContent.getId(),
                savedContent.getTitle(),
                savedContent.getDescription(),
                savedContent.getContentText(),
                savedContent.getImageUrl(),
                savedContent.isPublished()
        );
    }

    @Override
    public ContentResponseDTO updateContent(Long contentId, ContentRequestDTO contentRequestDTO) {
        Content existingContent = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id: " + contentId));

        existingContent.setTitle(contentRequestDTO.getTitle());
        existingContent.setDescription(contentRequestDTO.getDescription());
        existingContent.setContentText(contentRequestDTO.getContentText());
        existingContent.setImageUrl(contentRequestDTO.getImageUrl());

        Content updatedContent = contentRepository.save(existingContent);

        return new ContentResponseDTO(
                updatedContent.getId(),
                updatedContent.getTitle(),
                updatedContent.getDescription(),
                updatedContent.getContentText(),
                updatedContent.getImageUrl(),
                updatedContent.isPublished()
        );
    }

    @Override
    public void deleteContent(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id: " + contentId));
        contentRepository.delete(content);
    }

    @Override
    public ContentResponseDTO getContentById(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id: " + contentId));

        return new ContentResponseDTO(
                content.getId(),
                content.getTitle(),
                content.getDescription(),
                content.getContentText(),
                content.getImageUrl(),
                content.isPublished()
        );
    }

    @Override
    public List<ContentResponseDTO> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        return contents.stream()
                .map(content -> new ContentResponseDTO(
                        content.getId(),
                        content.getTitle(),
                        content.getDescription(),
                        content.getContentText(),
                        content.getImageUrl(),
                        content.isPublished()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ContentResponseDTO publishContent(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id: " + contentId));

        content.setPublished(true);
        Content updatedContent = contentRepository.save(content);

        return new ContentResponseDTO(
                updatedContent.getId(),
                updatedContent.getTitle(),
                updatedContent.getDescription(),
                updatedContent.getContentText(),
                updatedContent.getImageUrl(),
                updatedContent.isPublished()
        );
    }


    @Override
    public String uploadImage(MultipartFile file, Long contentId) {
        try {
            Optional<Content> contentOptional = contentRepository.findById(contentId);
            if (contentOptional.isPresent()) {
                Content content = contentOptional.get();
                content.setImageData(file.getBytes()); // Simpan gambar dalam bentuk byte array
                contentRepository.save(content);
                return "Gambar berhasil diunggah!";
            } else {
                return "Konten tidak ditemukan!";
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal mengupload gambar", e);
        }
    }

    @Override
    public byte[] getImage(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Konten tidak ditemukan"));
        return content.getImageData();
    }
}


