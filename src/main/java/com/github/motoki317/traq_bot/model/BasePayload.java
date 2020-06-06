package com.github.motoki317.traq_bot.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

class BasePayload {
    final LocalDateTime eventTime;

    BasePayload(String eventTime) {
        this.eventTime = Timestamp.valueOf(eventTime.replace("T", " ").replace("Z", "")).toLocalDateTime();
    }
}
