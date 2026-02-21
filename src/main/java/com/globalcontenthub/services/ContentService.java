package com.globalcontenthub.services;

import com.globalcontenthub.core.ContentManager;
import com.globalcontenthub.models.Content;

/**
 * Main service for content operations
 */
public class ContentService {
    private final ContentManager contentManager;
    private final ValidationService validationService;

    public ContentService() {
        this.contentManager = new ContentManager();
        this.validationService = new ValidationService();
    }

    public void createContent(Content content) {
        validationService.validateOrThrow(content);
        contentManager.createContent(content);
    }

    public Content getContent(String id) {
        return contentManager.retrieveContent(id);
    }

    public void updateContent(Content content) {
        validationService.validateOrThrow(content);
        contentManager.updateContent(content);
    }

    public void deleteContent(String id) {
        contentManager.deleteContent(id);
    }

    public int getContentCount() {
        return contentManager.getTotalContentCount();
    }
}

