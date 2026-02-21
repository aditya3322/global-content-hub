package com.globalcontenthub.userprofile.controller;

import com.globalcontenthub.userprofile.exceptions.UserProfileException;
import com.globalcontenthub.userprofile.models.UserProfile;
import com.globalcontenthub.userprofile.services.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    public UserProfileController() {
        this.service = new UserProfileService();
    }

    public ApiResponse<UserProfile> createProfile(UserProfile profile) {
        try {
            logger.info("Creating user profile for user: {}", profile.getUserId());
            UserProfile createdProfile = service.createProfile(profile);
            return new ApiResponse<>(true, "User profile created successfully", createdProfile, 201);
        } catch (UserProfileException e) {
            logger.error("Error creating profile: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<UserProfile> getProfileById(String id) {
        try {
            logger.info("Fetching user profile with ID: {}", id);
            UserProfile profile = service.getProfileById(id);
            return new ApiResponse<>(true, "User profile retrieved successfully", profile, 200);
        } catch (UserProfileException e) {
            logger.error("Error fetching profile: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<UserProfile> getProfileByUserId(String userId) {
        try {
            logger.info("Fetching user profile for user: {}", userId);
            UserProfile profile = service.getProfileByUserId(userId);
            return new ApiResponse<>(true, "User profile retrieved successfully", profile, 200);
        } catch (UserProfileException e) {
            logger.error("Error fetching profile by user ID: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<List<UserProfile>> getAllProfiles() {
        try {
            logger.info("Fetching all user profiles");
            List<UserProfile> profiles = service.getAllProfiles();
            return new ApiResponse<>(true, "User profiles retrieved successfully", profiles, 200);
        } catch (Exception e) {
            logger.error("Error fetching all profiles", e);
            return new ApiResponse<>(false, "Failed to retrieve user profiles", null, 500);
        }
    }

    public ApiResponse<List<UserProfile>> getActiveProfiles() {
        try {
            logger.info("Fetching all active user profiles");
            List<UserProfile> profiles = service.getActiveProfiles();
            return new ApiResponse<>(true, "Active user profiles retrieved successfully", profiles, 200);
        } catch (Exception e) {
            logger.error("Error fetching active profiles", e);
            return new ApiResponse<>(false, "Failed to retrieve active user profiles", null, 500);
        }
    }

    public ApiResponse<UserProfile> updateProfile(UserProfile profile) {
        try {
            logger.info("Updating user profile with ID: {}", profile.getId());
            UserProfile updatedProfile = service.updateProfile(profile);
            return new ApiResponse<>(true, "User profile updated successfully", updatedProfile, 200);
        } catch (UserProfileException e) {
            logger.error("Error updating profile: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<String> deleteProfile(String id) {
        try {
            logger.info("Deleting user profile with ID: {}", id);
            service.deleteProfile(id);
            return new ApiResponse<>(true, "User profile deleted successfully", "Profile with ID: " + id + " deleted", 200);
        } catch (UserProfileException e) {
            logger.error("Error deleting profile: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<UserProfile> deactivateProfile(String id) {
        try {
            logger.info("Deactivating user profile with ID: {}", id);
            UserProfile profile = service.deactivateProfile(id);
            return new ApiResponse<>(true, "User profile deactivated successfully", profile, 200);
        } catch (UserProfileException e) {
            logger.error("Error deactivating profile: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<UserProfile> activateProfile(String id) {
        try {
            logger.info("Activating user profile with ID: {}", id);
            UserProfile profile = service.activateProfile(id);
            return new ApiResponse<>(true, "User profile activated successfully", profile, 200);
        } catch (UserProfileException e) {
            logger.error("Error activating profile: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<List<UserProfile>> getProfilesByDepartment(String department) {
        try {
            logger.info("Fetching profiles for department: {}", department);
            List<UserProfile> profiles = service.getProfilesByDepartment(department);
            return new ApiResponse<>(true, "Profiles retrieved successfully", profiles, 200);
        } catch (UserProfileException e) {
            logger.error("Error fetching profiles by department: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<List<UserProfile>> getProfilesByDesignation(String designation) {
        try {
            logger.info("Fetching profiles for designation: {}", designation);
            List<UserProfile> profiles = service.getProfilesByDesignation(designation);
            return new ApiResponse<>(true, "Profiles retrieved successfully", profiles, 200);
        } catch (UserProfileException e) {
            logger.error("Error fetching profiles by designation: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public ApiResponse<UserProfile> updateProfileMetadata(String id, Map<String, String> metadata) {
        try {
            logger.info("Updating metadata for profile: {}", id);
            UserProfile profile = service.updateProfileMetadata(id, metadata);
            return new ApiResponse<>(true, "Profile metadata updated successfully", profile, 200);
        } catch (UserProfileException e) {
            logger.error("Error updating profile metadata: {}", e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), null, e.getHttpStatusCode());
        }
    }

    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
        private int statusCode;

        public ApiResponse(boolean success, String message, T data, int statusCode) {
            this.success = success;
            this.message = message;
            this.data = data;
            this.statusCode = statusCode;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public T getData() { return data; }
        public int getStatusCode() { return statusCode; }

        public void setSuccess(boolean success) { this.success = success; }
        public void setMessage(String message) { this.message = message; }
        public void setData(T data) { this.data = data; }
        public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    }
}

