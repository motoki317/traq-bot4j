package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCreatedEvent {
    private final BasePayload basePayload;
    private final Message message;

    public MessageCreatedEvent(@JsonProperty("eventTime") String eventTime,
                               @JsonProperty("message") Message message) {
        this.basePayload = new BasePayload(eventTime);
        this.message = message;
    }

    public BasePayload getBasePayload() {
        return basePayload;
    }

    public Message getMessage() {
        return message;
    }
}
