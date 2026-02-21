package com.globalcontenthub.models;

import java.util.UUID;

/**
 * Represents a user in the Global Content Hub
 */
public class User {
    private final String id;
    private String username;
    private String email;
    private String role;

    public User(String username, String email, String role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

