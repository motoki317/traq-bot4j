package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeftEvent {
    private final BasePayload basePayload;
    private final Channel channel;

    public LeftEvent(@JsonProperty("eventTime") String eventTime,
                       @JsonProperty("channel") Channel channel) {
        this.basePayload = new BasePayload(eventTime);
        this.channel = channel;
    }

    public BasePayload getBasePayload() {
        return basePayload;
    }

    public Channel getChannel() {
        return channel;
    }
}
