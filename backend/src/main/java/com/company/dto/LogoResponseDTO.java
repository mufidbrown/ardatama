package com.company.dto;

public class LogoResponseDTO {
    private Long id;
    private String image;
    private String contentType;
    private String username; // Tambahkan username

    public LogoResponseDTO(Long id, String image, String contentType, String username) {
        this.id = id;
        this.image = image;
        this.contentType = contentType;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
