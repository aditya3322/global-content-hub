package com.globalcontenthub;

import com.globalcontenthub.models.Content;
import com.globalcontenthub.models.ContentType;
import com.globalcontenthub.services.ContentService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for ContentService
 */
public class ContentServiceTest {
    private ContentService contentService;

    @Before
    public void setUp() {
        contentService = new ContentService();
    }

    @Test
    public void testCreateContent() {
        Content content = new Content("Test Title", "Test Description", ContentType.TEXT);
        assertDoesNotThrow(() -> contentService.createContent(content));
        assertEquals(1, contentService.getContentCount());
    }

    @Test
    public void testRetrieveContent() {
        Content content = new Content("Test Title", "Test Description", ContentType.TEXT);
        contentService.createContent(content);
        Content retrieved = contentService.getContent(content.getId());
        assertNotNull(retrieved);
        assertEquals("Test Title", retrieved.getTitle());
    }

    @Test
    public void testInvalidContent() {
        Content invalidContent = new Content("", "Description", ContentType.TEXT);
        assertThrows(IllegalArgumentException.class, () -> contentService.createContent(invalidContent));
    }

    private void assertDoesNotThrow(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            fail("Expected no exception but got: " + e.getMessage());
        }
    }
}

