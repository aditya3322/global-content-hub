package com.globalcontenthub.services;

import com.globalcontenthub.models.Content;

/**
 * Service for validating content
 */
public class ValidationService {

    public boolean validateContent(Content content) {
        return content != null &&
                isValidTitle(content.getTitle()) &&
                content.getType() != null;
    }

    public boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.length() <= 500;
    }

    public boolean isValidDescription(String description) {
        return description == null || description.length() <= 5000;
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public void validateOrThrow(Content content) {
        if (!validateContent(content)) {
            throw new IllegalArgumentException("Content validation failed");
        }
    }
}

