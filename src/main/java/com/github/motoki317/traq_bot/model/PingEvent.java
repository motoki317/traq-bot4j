package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PingEvent {
    private final BasePayload basePayload;

    public PingEvent(@JsonProperty("eventTime") String eventTime) {
        this.basePayload = new BasePayload(eventTime);
    }

    public LocalDateTime getEventTime() {
        return basePayload.eventTime;
    }
}
