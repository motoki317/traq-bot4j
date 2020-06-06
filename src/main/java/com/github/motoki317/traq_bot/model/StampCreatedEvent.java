package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StampCreatedEvent {
    private final BasePayload basePayload;
    private final String id;
    private final String name;
    private final String fileId;
    private final User creator;

    public StampCreatedEvent(@JsonProperty("eventTime") String eventTime,
                             @JsonProperty("id") String id,
                             @JsonProperty("name") String name,
                             @JsonProperty("fileId") String fileId,
                             @JsonProperty("creator") User creator) {
        this.basePayload = new BasePayload(eventTime);
        this.id = id;
        this.name = name;
        this.fileId = fileId;
        this.creator = creator;
    }

    public BasePayload getBasePayload() {
        return basePayload;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFileId() {
        return fileId;
    }

    public User getCreator() {
        return creator;
    }
}
