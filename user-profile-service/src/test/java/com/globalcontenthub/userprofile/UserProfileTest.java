package com.globalcontenthub.userprofile;

import com.globalcontenthub.userprofile.controller.UserProfileController;
import com.globalcontenthub.userprofile.exceptions.UserProfileException;
import com.globalcontenthub.userprofile.models.UserProfile;
import com.globalcontenthub.userprofile.services.UserProfileService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileTest {
    private UserProfileService service;
    private UserProfileController controller;
    private UserProfile testProfile;

    @Before
    public void setUp() {
        service = new UserProfileService();
        controller = new UserProfileController(service);
        testProfile = new UserProfile("user123", "John", "Doe", "john.doe@example.com");
        testProfile.setDepartment("Engineering");
        testProfile.setDesignation("Software Engineer");
    }

    @Test
    public void testCreateProfileSuccess() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        assertNotNull(created);
        assertEquals("user123", created.getUserId());
    }

    @Test
    public void testGetProfileById() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        UserProfile retrieved = service.getProfileById(created.getId());
        assertNotNull(retrieved);
        assertEquals(created.getId(), retrieved.getId());
    }

    @Test
    public void testGetProfileByUserId() throws UserProfileException {
        service.createProfile(testProfile);
        UserProfile retrieved = service.getProfileByUserId("user123");
        assertNotNull(retrieved);
        assertEquals("user123", retrieved.getUserId());
    }

    @Test
    public void testGetAllProfiles() throws UserProfileException {
        service.createProfile(testProfile);
        assertEquals(1, service.getAllProfiles().size());
    }

    @Test
    public void testUpdateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        created.setDesignation("Senior Software Engineer");
        UserProfile updated = service.updateProfile(created);
        assertEquals("Senior Software Engineer", updated.getDesignation());
    }

    @Test
    public void testDeleteProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        service.deleteProfile(created.getId());
        assertEquals(0, service.getAllProfiles().size());
    }

    @Test
    public void testDeactivateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        UserProfile deactivated = service.deactivateProfile(created.getId());
        assertFalse(deactivated.isActive());
    }

    @Test
    public void testActivateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        service.deactivateProfile(created.getId());
        UserProfile activated = service.activateProfile(created.getId());
        assertTrue(activated.isActive());
    }

    @Test
    public void testGetProfilesByDepartment() throws UserProfileException {
        service.createProfile(testProfile);
        assertEquals(1, service.getProfilesByDepartment("Engineering").size());
    }

    @Test
    public void testGetActiveProfiles() throws UserProfileException {
        service.createProfile(testProfile);
        assertEquals(1, service.getActiveProfiles().size());
    }

    @Test
    public void testCreateProfileWithInvalidEmailThrowsException() {
        UserProfile invalidProfile = new UserProfile("user123", "John", "Doe", "invalid-email");
        assertThrows(UserProfileException.class, () -> service.createProfile(invalidProfile));
    }

    @Test
    public void testGetNonExistentProfileThrowsException() {
        assertThrows(UserProfileException.class, () -> service.getProfileById("nonexistent"));
    }

    @Test
    public void testControllerCreateProfile() throws UserProfileException {
        UserProfileController.ApiResponse<UserProfile> response = controller.createProfile(testProfile);
        assertTrue(response.isSuccess());
        assertEquals(201, response.getStatusCode());
        assertNotNull(response.getData());
    }

    @Test
    public void testControllerGetProfileById() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        UserProfileController.ApiResponse<UserProfile> response = controller.getProfileById(created.getId());
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testControllerDeleteProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        UserProfileController.ApiResponse<String> response = controller.deleteProfile(created.getId());
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testControllerErrorHandling() {
        UserProfileController.ApiResponse<UserProfile> response = controller.getProfileById("nonexistent");
        assertFalse(response.isSuccess());
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testUserProfileModel() {
        UserProfile profile = new UserProfile("u1", "John", "Doe", "john@example.com");
        assertNotNull(profile.getId());
        assertEquals("John Doe", profile.getFullName());
        assertTrue(profile.isActive());
    }

    @Test
    public void testGetProfilesByDesignation() throws UserProfileException {
        service.createProfile(testProfile);
        assertEquals(1, service.getProfilesByDesignation("Software Engineer").size());
    }

    @Test
    public void testUpdateProfileMetadata() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        java.util.Map<String, String> metadata = new java.util.HashMap<>();
        metadata.put("skill", "java");
        UserProfile updated = service.updateProfileMetadata(created.getId(), metadata);
        assertEquals("java", updated.getMetadata().get("skill"));
    }

    @Test
    public void testControllerGetAllProfiles() throws UserProfileException {
        service.createProfile(testProfile);
        UserProfileController.ApiResponse<?> response = controller.getAllProfiles();
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGetActiveProfilesCount() throws UserProfileException {
        service.createProfile(testProfile);
        service.deactivateProfile(service.getProfileByUserId("user123").getId());
        assertEquals(0, service.getActiveProfiles().size());
    }

    @Test
    public void testUpdateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        created.setPhoneNumber("1234567890");
        UserProfile updated = service.updateProfile(created);
        assertEquals("1234567890", updated.getPhoneNumber());
    }

    @Test
    public void testControllerUpdateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        created.setDesignation("Manager");
        UserProfileController.ApiResponse<UserProfile> response = controller.updateProfile(created);
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testControllerDeactivateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        UserProfileController.ApiResponse<UserProfile> response = controller.deactivateProfile(created.getId());
        assertTrue(response.isSuccess());
        assertFalse(response.getData().isActive());
    }

    @Test
    public void testControllerActivateProfile() throws UserProfileException {
        UserProfile created = service.createProfile(testProfile);
        service.deactivateProfile(created.getId());
        UserProfileController.ApiResponse<UserProfile> response = controller.activateProfile(created.getId());
        assertTrue(response.isSuccess());
        assertTrue(response.getData().isActive());
    }

    @Test
    public void testControllerGetProfilesByDepartment() throws UserProfileException {
        service.createProfile(testProfile);
        UserProfileController.ApiResponse<?> response = controller.getProfilesByDepartment("Engineering");
        assertTrue(response.isSuccess());
    }

    @Test
    public void testProfileFullName() {
        UserProfile profile = new UserProfile();
        profile.setFirstName("Jane");
        profile.setLastName("Smith");
        assertEquals("Jane Smith", profile.getFullName());
    }
}

