package com.globalcontenthub.models;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a piece of content in the Global Content Hub
 */
public class Content {
    private final String id;
    private String title;
    private String description;
    private ContentType type;
    private String data;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Content(String title, String description, ContentType type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.type = type;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
        this.updatedAt = LocalDateTime.now();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        this.updatedAt = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", author='" + author + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

