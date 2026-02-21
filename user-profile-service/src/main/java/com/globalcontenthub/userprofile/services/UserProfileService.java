package com.globalcontenthub.userprofile.services;

import com.globalcontenthub.userprofile.exceptions.UserProfileException;
import com.globalcontenthub.userprofile.models.UserProfile;
import com.globalcontenthub.userprofile.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class UserProfileService {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);
    private final UserProfileRepository repository;

    public UserProfileService() {
        this.repository = new UserProfileRepository();
    }

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile createProfile(UserProfile profile) throws UserProfileException {
        try {
            validateProfile(profile);
            logger.info("Creating user profile for user: {}", profile.getUserId());
            return repository.create(profile);
        } catch (UserProfileException e) {
            logger.error("Error creating profile: {}", e.getMessage());
            throw e;
        }
    }

    public UserProfile getProfileById(String id) throws UserProfileException {
        if (id == null || id.trim().isEmpty()) {
            throw new UserProfileException("Profile ID cannot be null or empty", "INVALID_ID", 400);
        }
        UserProfile profile = repository.findById(id);
        if (profile == null) {
            throw new UserProfileException("User profile not found with ID: " + id, "PROFILE_NOT_FOUND", 404);
        }
        return profile;
    }

    public UserProfile getProfileByUserId(String userId) throws UserProfileException {
        if (userId == null || userId.trim().isEmpty()) {
            throw new UserProfileException("User ID cannot be null or empty", "INVALID_USER_ID", 400);
        }
        UserProfile profile = repository.findByUserId(userId);
        if (profile == null) {
            throw new UserProfileException("User profile not found for user: " + userId, "PROFILE_NOT_FOUND", 404);
        }
        return profile;
    }

    public List<UserProfile> getAllProfiles() {
        logger.info("Fetching all user profiles");
        return repository.findAll();
    }

    public List<UserProfile> getActiveProfiles() {
        logger.info("Fetching all active user profiles");
        return repository.findAllActive();
    }

    public UserProfile updateProfile(UserProfile profile) throws UserProfileException {
        try {
            if (!repository.exists(profile.getId())) {
                throw new UserProfileException("User profile not found with ID: " + profile.getId(), "PROFILE_NOT_FOUND", 404);
            }
            validateProfile(profile);
            profile.setUpdatedAt(LocalDateTime.now());
            logger.info("Updating user profile with ID: {}", profile.getId());
            return repository.update(profile);
        } catch (UserProfileException e) {
            logger.error("Error updating profile: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteProfile(String id) throws UserProfileException {
        if (id == null || id.trim().isEmpty()) {
            throw new UserProfileException("Profile ID cannot be null or empty", "INVALID_ID", 400);
        }
        if (!repository.exists(id)) {
            throw new UserProfileException("User profile not found with ID: " + id, "PROFILE_NOT_FOUND", 404);
        }
        logger.info("Deleting user profile with ID: {}", id);
        repository.delete(id);
    }

    public UserProfile deactivateProfile(String id) throws UserProfileException {
        UserProfile profile = getProfileById(id);
        profile.setActive(false);
        profile.setUpdatedAt(LocalDateTime.now());
        logger.info("Deactivating user profile with ID: {}", id);
        return repository.update(profile);
    }

    public UserProfile activateProfile(String id) throws UserProfileException {
        UserProfile profile = getProfileById(id);
        profile.setActive(true);
        profile.setUpdatedAt(LocalDateTime.now());
        logger.info("Activating user profile with ID: {}", id);
        return repository.update(profile);
    }

    public List<UserProfile> getProfilesByDepartment(String department) throws UserProfileException {
        if (department == null || department.trim().isEmpty()) {
            throw new UserProfileException("Department cannot be null or empty", "INVALID_DEPARTMENT", 400);
        }
        logger.info("Fetching profiles for department: {}", department);
        return repository.findByDepartment(department);
    }

    public List<UserProfile> getProfilesByDesignation(String designation) throws UserProfileException {
        if (designation == null || designation.trim().isEmpty()) {
            throw new UserProfileException("Designation cannot be null or empty", "INVALID_DESIGNATION", 400);
        }
        logger.info("Fetching profiles for designation: {}", designation);
        return repository.findByDesignation(designation);
    }

    public UserProfile updateProfileMetadata(String id, Map<String, String> metadata) throws UserProfileException {
        UserProfile profile = getProfileById(id);
        profile.setMetadata(metadata);
        profile.setUpdatedAt(LocalDateTime.now());
        logger.info("Updating metadata for profile: {}", id);
        return repository.update(profile);
    }

    private void validateProfile(UserProfile profile) throws UserProfileException {
        if (profile == null) {
            throw new UserProfileException("User profile cannot be null", "NULL_PROFILE", 400);
        }
        if (profile.getUserId() == null || profile.getUserId().trim().isEmpty()) {
            throw new UserProfileException("User ID is required", "MISSING_USER_ID", 400);
        }
        if (profile.getFirstName() == null || profile.getFirstName().trim().isEmpty()) {
            throw new UserProfileException("First name is required", "MISSING_FIRST_NAME", 400);
        }
        if (profile.getLastName() == null || profile.getLastName().trim().isEmpty()) {
            throw new UserProfileException("Last name is required", "MISSING_LAST_NAME", 400);
        }
        if (profile.getEmail() == null || profile.getEmail().trim().isEmpty()) {
            throw new UserProfileException("Email is required", "MISSING_EMAIL", 400);
        }
        if (!isValidEmail(profile.getEmail())) {
            throw new UserProfileException("Invalid email format", "INVALID_EMAIL", 400);
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}

