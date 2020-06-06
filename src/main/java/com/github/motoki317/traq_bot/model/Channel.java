package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    private final String id;
    private final String name;
    private final String path;
    private final String parentId;
    private final User creator;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Channel(@JsonProperty("id") String id,
                   @JsonProperty("name") String name,
                   @JsonProperty("path") String path,
                   @JsonProperty("parentId") String parentId,
                   @JsonProperty("creator") User creator,
                   @JsonProperty("createdAt") String createdAt,
                   @JsonProperty("updatedAt") String updatedAt) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.parentId = parentId;
        this.creator = creator;
        this.createdAt = Timestamp.valueOf(createdAt.replace("T", " ").replace("Z", "")).toLocalDateTime();
        this.updatedAt = Timestamp.valueOf(updatedAt.replace("T", " ").replace("Z", "")).toLocalDateTime();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getParentId() {
        return parentId;
    }

    public User getCreator() {
        return creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
