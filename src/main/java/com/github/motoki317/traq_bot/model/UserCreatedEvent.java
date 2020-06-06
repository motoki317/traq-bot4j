package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreatedEvent {
    private final BasePayload basePayload;
    private final User user;

    public UserCreatedEvent(@JsonProperty("eventTime") String eventTime,
                            @JsonProperty("user") User user) {
        this.basePayload = new BasePayload(eventTime);
        this.user = user;
    }

    public BasePayload getBasePayload() {
        return basePayload;
    }

    public User getUser() {
        return user;
    }
}
