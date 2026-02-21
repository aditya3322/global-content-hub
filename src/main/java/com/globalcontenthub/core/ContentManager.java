package com.globalcontenthub.core;

import com.globalcontenthub.models.Content;

/**
 * Manages content lifecycle operations
 */
public class ContentManager {
    private final ContentStore store;

    public ContentManager() {
        this.store = new ContentStore();
    }

    public void createContent(Content content) {
        validateContent(content);
        store.save(content);
    }

    public Content retrieveContent(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Content ID cannot be null or empty");
        }
        return store.get(id);
    }

    public void updateContent(Content content) {
        validateContent(content);
        if (!store.exists(content.getId())) {
            throw new IllegalArgumentException("Content with ID " + content.getId() + " not found");
        }
        store.save(content);
    }

    public void deleteContent(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Content ID cannot be null or empty");
        }
        store.delete(id);
    }

    private void validateContent(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (content.getTitle() == null || content.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Content title cannot be null or empty");
        }
        if (content.getType() == null) {
            throw new IllegalArgumentException("Content type cannot be null");
        }
    }

    public int getTotalContentCount() {
        return store.size();
    }
}

