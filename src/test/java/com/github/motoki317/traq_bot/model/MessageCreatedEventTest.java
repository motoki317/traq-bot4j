package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageCreatedEventTest {
    @Test
    void testBinding() throws JsonProcessingException {
        String body = "{\n" +
                "  \"eventTime\": \"2019-05-08T13:33:51.690308239Z\",\n" +
                "  \"message\": {\n" +
                "    \"id\": \"bc9106b3-f9b2-4eca-9ba1-72b39b40954e\",\n" +
                "    \"user\": {\n" +
                "      \"id\": \"dfdff0c9-5de0-46ee-9721-2525e8bb3d45\",\n" +
                "      \"name\": \"takashi_trap\",\n" +
                "      \"displayName\": \"寺田 健二\",\n" +
                "      \"iconId\": \"2bc06cda-bdb9-4a68-8000-62f907f36a92\",\n" +
                "      \"bot\": false\n" +
                "    },\n" +
                "    \"channelId\": \"9aba50da-f605-4cd0-a428-5e4558cb911e\",\n" +
                "    \"text\": \"!{\\\"type\\\": \\\"user\\\", \\\"raw\\\": \\\"@takashi_trap\\\", \\\"id\\\": \\\"dfdff0c9-5de0-46ee-9721-2525e8bb3d45\\\"} こんにちは\",\n" +
                "    \"plainText\": \"@takashi_trap こんにちは\",\n" +
                "    \"embedded\": [\n" +
                "      {\n" +
                "        \"raw\": \"@takashi_trap\",\n" +
                "        \"type\": \"user\",\n" +
                "        \"id\": \"dfdff0c9-5de0-46ee-9721-2525e8bb3d45\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"createdAt\": \"2019-05-08T13:33:51.632149265Z\",\n" +
                "    \"updatedAt\": \"2019-05-08T13:33:51.632149265Z\"\n" +
                "  }\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        MessageCreatedEvent event = mapper.readValue(body, MessageCreatedEvent.class);
        assert event != null;
        assertEquals(event.getMessage().getChannelId(), "9aba50da-f605-4cd0-a428-5e4558cb911e");
        assertEquals(event.getMessage().getPlainText(), "@takashi_trap こんにちは");
        assertEquals(event.getMessage().getEmbedded().length, 1);
        assertEquals(event.getMessage().getEmbedded()[0].getRaw(), "@takashi_trap");
    }
}