package com.globalcontenthub.models;

/**
 * Enumeration of supported content types
 */
public enum ContentType {
    TEXT("text/plain"),
    HTML("text/html"),
    MARKDOWN("text/markdown"),
    IMAGE("image/*"),
    VIDEO("video/*"),
    DOCUMENT("application/pdf"),
    JSON("application/json"),
    XML("application/xml"),
    UNKNOWN("application/octet-stream");

    private final String mimeType;

    ContentType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}

