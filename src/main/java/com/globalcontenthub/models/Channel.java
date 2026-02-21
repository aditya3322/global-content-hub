package com.globalcontenthub.models;

import java.util.UUID;

/**
 * Represents a distribution channel for content
 */
public class Channel {
    private final String id;
    private String name;
    private String description;
    private String type;
    private boolean active;

    public Channel(String name, String type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.active = true;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                '}';
    }
}

