package com.github.motoki317.traq_bot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChannelCreatedEventTest {
    @Test
    void testBinding() throws JsonProcessingException {
        String body = "{\n" +
                "  \"eventTime\": \"2019-05-08T13:45:51.506206852Z\",\n" +
                "  \"channel\": {\n" +
                "    \"id\": \"711afb4c-23e7-46dc-b845-5160f7088ce9\",\n" +
                "    \"name\": \"yamada\",\n" +
                "    \"path\": \"#gps/yamada\",\n" +
                "    \"parentId\": \"ea452867-553b-4808-a14f-a47ee0009ee6\",\n" +
                "    \"creator\": {\n" +
                "      \"id\": \"dfdff0c9-5de0-46ee-9721-2525e8bb3d45\",\n" +
                "      \"name\": \"takashi_trap\",\n" +
                "      \"displayName\": \"takashi\",\n" +
                "      \"iconId\": \"2bc06cda-bdb9-4a68-8000-62f907f36a92\",\n" +
                "      \"bot\": false\n" +
                "    },\n" +
                "    \"createdAt\": \"2019-05-08T13:45:51.487718Z\",\n" +
                "    \"updatedAt\": \"2019-05-08T13:45:51.487718Z\"\n" +
                "  }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        ChannelCreatedEvent event = mapper.readValue(body, ChannelCreatedEvent.class);
        assert event != null;
        assertEquals(event.getChannel().getId(), "711afb4c-23e7-46dc-b845-5160f7088ce9");
        assertEquals(event.getChannel().getCreator().getDisplayName(), "takashi");
    }
}