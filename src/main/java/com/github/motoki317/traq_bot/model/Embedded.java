package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedded {
    private final String raw;
    private final String type;
    private final String id;

    public Embedded(@JsonProperty("raw") String raw,
                    @JsonProperty("type") String type,
                    @JsonProperty("id") String id) {
        this.raw = raw;
        this.type = type;
        this.id = id;
    }

    public String getRaw() {
        return raw;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
