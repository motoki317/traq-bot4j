package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagAddedEvent {
    private final BasePayload basePayload;
    private final String tagId;
    private final String tag;

    public TagAddedEvent(@JsonProperty("eventTime") String eventTime,
                         @JsonProperty("tagId") String tagId,
                         @JsonProperty("tag") String tag) {
        this.basePayload = new BasePayload(eventTime);
        this.tagId = tagId;
        this.tag = tag;
    }

    public BasePayload getBasePayload() {
        return basePayload;
    }

    public String getTagId() {
        return tagId;
    }

    public String getTag() {
        return tag;
    }
}
