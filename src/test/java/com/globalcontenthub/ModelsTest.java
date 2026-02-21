package com.globalcontenthub;

import com.globalcontenthub.models.Content;
import com.globalcontenthub.models.ContentType;
import com.globalcontenthub.models.User;
import com.globalcontenthub.models.Channel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for Models
 */
public class ModelsTest {

    @Test
    public void testContentCreation() {
        Content content = new Content("Title", "Description", ContentType.TEXT);
        assertNotNull(content.getId());
        assertEquals("Title", content.getTitle());
        assertEquals(ContentType.TEXT, content.getType());
    }

    @Test
    public void testUserCreation() {
        User user = new User("john_doe", "john@example.com", "admin");
        assertNotNull(user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testChannelCreation() {
        Channel channel = new Channel("Email Channel", "EMAIL");
        assertNotNull(channel.getId());
        assertEquals("Email Channel", channel.getName());
        assertTrue(channel.isActive());
    }

    @Test
    public void testContentTypeEnums() {
        assertEquals("text/plain", ContentType.TEXT.getMimeType());
        assertEquals("application/json", ContentType.JSON.getMimeType());
    }
}

