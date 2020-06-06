package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelTopicChangedEvent {
    private final BasePayload basePayload;
    private final Channel channel;
    private final String topic;
    private final User updater;

    public ChannelTopicChangedEvent(@JsonProperty("eventTime") String eventTime,
                                    @JsonProperty("channel") Channel channel,
                                    @JsonProperty("topic") String topic,
                                    @JsonProperty("user") User updater) {
        this.basePayload = new BasePayload(eventTime);
        this.channel = channel;
        this.topic = topic;
        this.updater = updater;
    }

    public BasePayload getBasePayload() {
        return basePayload;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getTopic() {
        return topic;
    }

    public User getUpdater() {
        return updater;
    }
}
