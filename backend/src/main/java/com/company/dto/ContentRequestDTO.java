package com.company.dto;

public class ContentRequestDTO {
    private String title;
    private String description;
    private String contentText;
    private String imageUrl;

    // Constructor
    public ContentRequestDTO(String title, String description, String contentText, String imageUrl) {
        this.title = title;
        this.description = description;
        this.contentText = contentText;
        this.imageUrl = imageUrl;
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
}
