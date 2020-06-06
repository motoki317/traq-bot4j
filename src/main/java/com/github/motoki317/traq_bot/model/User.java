package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private final String id;
    private final String name;
    private final String displayName;
    private final String iconId;
    private final boolean bot;

    public User(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("displayName") String displayName,
                @JsonProperty("iconId") String iconId,
                @JsonProperty("bot") boolean bot) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.iconId = iconId;
        this.bot = bot;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIconId() {
        return iconId;
    }

    public boolean isBot() {
        return bot;
    }
}
