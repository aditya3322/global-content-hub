package com.globalcontenthub.userprofile.repository;

import com.globalcontenthub.userprofile.models.UserProfile;
import java.util.*;

public class UserProfileRepository {
    private final Map<String, UserProfile> storage = new HashMap<>();

    public UserProfile create(UserProfile profile) {
        storage.put(profile.getId(), profile);
        return profile;
    }

    public UserProfile findById(String id) {
        return storage.get(id);
    }

    public UserProfile findByUserId(String userId) {
        return storage.values().stream()
                .filter(profile -> profile.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<UserProfile> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<UserProfile> findAllActive() {
        return storage.values().stream()
                .filter(UserProfile::isActive)
                .toList();
    }

    public UserProfile update(UserProfile profile) {
        if (!storage.containsKey(profile.getId())) {
            return null;
        }
        storage.put(profile.getId(), profile);
        return profile;
    }

    public boolean delete(String id) {
        return storage.remove(id) != null;
    }

    public boolean exists(String id) {
        return storage.containsKey(id);
    }

    public List<UserProfile> findByDepartment(String department) {
        return storage.values().stream()
                .filter(profile -> department.equals(profile.getDepartment()))
                .toList();
    }

    public List<UserProfile> findByDesignation(String designation) {
        return storage.values().stream()
                .filter(profile -> designation.equals(profile.getDesignation()))
                .toList();
    }

    public void clear() {
        storage.clear();
    }

    public int count() {
        return storage.size();
    }
}

