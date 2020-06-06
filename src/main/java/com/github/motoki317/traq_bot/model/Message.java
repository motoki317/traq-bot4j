package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private final String id;
    private final User user;
    private final String channelId;
    private final String text;
    private final String plainText;
    private final Embedded[] embedded;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Message(@JsonProperty("id") String id,
                   @JsonProperty("user") User user,
                   @JsonProperty("channelId") String channelId,
                   @JsonProperty("text") String text,
                   @JsonProperty("plainText") String plainText,
                   @JsonProperty("embedded") Embedded[] embedded,
                   @JsonProperty("createdAt") String createdAt,
                   @JsonProperty("updatedAt") String updatedAt) {
        this.id = id;
        this.user = user;
        this.channelId = channelId;
        this.text = text;
        this.plainText = plainText;
        this.embedded = embedded;
        this.createdAt = Timestamp.valueOf(createdAt.replace("T", " ").replace("Z", "")).toLocalDateTime();
        this.updatedAt = Timestamp.valueOf(updatedAt.replace("T", " ").replace("Z", "")).toLocalDateTime();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getText() {
        return text;
    }

    public String getPlainText() {
        return plainText;
    }

    public Embedded[] getEmbedded() {
        return embedded;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
