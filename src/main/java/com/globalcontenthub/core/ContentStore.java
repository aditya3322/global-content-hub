package com.globalcontenthub.core;

import com.globalcontenthub.models.Content;
import java.util.*;

/**
 * Manages content storage and retrieval
 */
public class ContentStore {
    private final Map<String, Content> store;

    public ContentStore() {
        this.store = new HashMap<>();
    }

    public void save(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        store.put(content.getId(), content);
    }

    public Content get(String id) {
        return store.get(id);
    }

    public void delete(String id) {
        store.remove(id);
    }

    public List<Content> getAll() {
        return new ArrayList<>(store.values());
    }

    public boolean exists(String id) {
        return store.containsKey(id);
    }

    public int size() {
        return store.size();
    }

    public void clear() {
        store.clear();
    }
}

