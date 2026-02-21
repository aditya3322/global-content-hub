package com.globalcontenthub.services;

import com.globalcontenthub.models.Content;
import com.globalcontenthub.models.Channel;
import java.util.*;

/**
 * Service for distributing content to channels
 */
public class DistributionService {
    private final List<Channel> channels;
    private final Map<String, Set<String>> contentDistribution; // contentId -> channelIds

    public DistributionService() {
        this.channels = new ArrayList<>();
        this.contentDistribution = new HashMap<>();
    }

    public void registerChannel(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }
        channels.add(channel);
    }

    public void distributeContent(String contentId, String channelId) {
        if (contentId == null || contentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Content ID cannot be null or empty");
        }
        if (channelId == null || channelId.trim().isEmpty()) {
            throw new IllegalArgumentException("Channel ID cannot be null or empty");
        }

        contentDistribution.computeIfAbsent(contentId, k -> new HashSet<>()).add(channelId);
    }

    public Set<String> getDistributionChannels(String contentId) {
        return contentDistribution.getOrDefault(contentId, Collections.emptySet());
    }

    public List<Channel> getAllChannels() {
        return new ArrayList<>(channels);
    }

    public int getTotalChannels() {
        return channels.size();
    }
}

