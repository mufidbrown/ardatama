package com.company.dto;

public class ContentResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String contentText;
    private String imageUrl;
    private boolean isPublished;

    // Constructor
    public ContentResponseDTO(Long id, String title, String description, String contentText, String imageUrl, boolean isPublished) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contentText = contentText;
        this.imageUrl = imageUrl;
        this.isPublished = isPublished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
}
