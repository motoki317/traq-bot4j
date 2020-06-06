package com.github.motoki317.traq_bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.motoki317.traq4j.ApiClient;
import com.github.motoki317.traq4j.api.MessageApi;
import com.github.motoki317.traq4j.model.PostMessageRequest;
import com.github.motoki317.traq_bot.model.*;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BotServer {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final DateFormat logFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final String BOT_TOKEN_HEADER = "X-TRAQ-BOT-TOKEN";
    private static final String BOT_EVENT_HEADER = "X-TRAQ-BOT-EVENT";

    private final ApiClient client;
    private final MessageApi messageApi;

    // Threads for actual event processing
    private final ExecutorService eventExecutors;
    private final EventHandlers eventHandlers;

    private final HttpServer server;
    private final String verificationToken;
    private final int port;

    /**
     * Creates a new traQ bot server.
     * @param verificationToken Verification token to verify the request from traQ server.
     * @param accessToken Access token to fetch traQ API endpoints with.
     * @param port Port to listen on.
     * @throws IOException thrown on failure to binding to the given port.
     */
    public BotServer(String verificationToken, String accessToken, int port, EventHandlers eventHandlers) throws IOException {
        if (verificationToken == null || accessToken == null) {
            throw new RuntimeException("Verification token or access token is null");
        }

        this.verificationToken = verificationToken;

        this.client = new ApiClient();
        this.client.addDefaultHeader("Authorization", "Bearer " + accessToken);
        this.messageApi = new MessageApi(this.client);

        this.eventExecutors = Executors.newFixedThreadPool(5);
        this.eventHandlers = eventHandlers;

        this.server = HttpServer.create(new InetSocketAddress(port), 5);
        this.server.createContext("/", this::handle);
        this.port = port;
    }

    /**
     * Returns traQ API client for use of other endpoints.
     * @return API client.
     */
    public ApiClient getClient() {
        return client;
    }

    /**
     * Starts the http server and listens on the given port.
     */
    public void start() {
        System.out.println("Starting bot server on port " + port + "...");
        this.server.start();
    }

    private void handle(HttpExchange exchange) throws IOException {
        System.out.printf("%s [%s] Received %s request from %s\n",
                logFormat.format(new Date()),
                exchange.getRequestURI().getPath(),
                exchange.getRequestMethod(),
                exchange.getRemoteAddress().toString());

        Headers headers = exchange.getRequestHeaders();

        // Check verification token
        String token = headers.getFirst(BOT_TOKEN_HEADER);
        if (!this.verificationToken.equals(token)) {
            System.out.println("Bad verification token");
            respond(exchange, 400);
            return;
        }

        String eventName = headers.getFirst(BOT_EVENT_HEADER);
        if (eventName == null) {
            System.out.println("No event name on request header");
            respond(exchange, 400);
            return;
        }

        // Handle events in different thread
        String body = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                .lines().collect(Collectors.joining("\n"));
        respond(exchange, 204);

        System.out.println("Detected event " + eventName);
        this.eventExecutors.execute(() -> {
            try {
                this.handleEvent(eventName, body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleEvent(String eventName, String body) throws IOException {
        switch (eventName) {
            case "PING":
                PingEvent pingEvent = mapper.readValue(body, PingEvent.class);
                this.eventHandlers.handlePing(pingEvent);
                break;
            case "JOINED":
                JoinedEvent joinedEvent = mapper.readValue(body, JoinedEvent.class);
                this.eventHandlers.handleJoined(joinedEvent, newResponder(joinedEvent.getChannel().getId()));
                break;
            case "LEFT":
                LeftEvent leftEvent = mapper.readValue(body, LeftEvent.class);
                this.eventHandlers.handleLeft(leftEvent, newResponder(leftEvent.getChannel().getId()));
                break;
            case "MESSAGE_CREATED":
                MessageCreatedEvent messageCreatedEvent = mapper.readValue(body, MessageCreatedEvent.class);
                this.eventHandlers.handleMessageCreated(messageCreatedEvent, newResponder(messageCreatedEvent.getMessage().getChannelId()));
                break;
            case "DIRECT_MESSAGE_CREATED":
                DirectMessageCreatedEvent directMessageCreatedEvent = mapper.readValue(body, DirectMessageCreatedEvent.class);
                this.eventHandlers.handleDirectMessageCreated(directMessageCreatedEvent, newResponder(directMessageCreatedEvent.getMessage().getChannelId()));
                break;
            case "CHANNEL_CREATED":
                ChannelCreatedEvent channelCreatedEvent = mapper.readValue(body, ChannelCreatedEvent.class);
                this.eventHandlers.handleChannelCreated(channelCreatedEvent, newResponder(channelCreatedEvent.getChannel().getId()));
                break;
            case "CHANNEL_TOPIC_CHANGED":
                ChannelTopicChangedEvent channelTopicChangedEvent = mapper.readValue(body, ChannelTopicChangedEvent.class);
                this.eventHandlers.handleChannelTopicChanged(channelTopicChangedEvent, newResponder(channelTopicChangedEvent.getChannel().getId()));
                break;
            case "USER_CREATED":
                UserCreatedEvent userCreatedEvent = mapper.readValue(body, UserCreatedEvent.class);
                this.eventHandlers.handleUserCreated(userCreatedEvent);
                break;
            case "STAMP_CREATED":
                StampCreatedEvent stampCreatedEvent = mapper.readValue(body, StampCreatedEvent.class);
                this.eventHandlers.handleStampCreated(stampCreatedEvent);
                break;
            case "TAG_ADDED":
                TagAddedEvent tagAddedEvent = mapper.readValue(body, TagAddedEvent.class);
                this.eventHandlers.handleTagAdded(tagAddedEvent);
                break;
            case "TAG_REMOVED":
                TagRemovedEvent tagRemovedEvent = mapper.readValue(body, TagRemovedEvent.class);
                this.eventHandlers.handleTagRemoved(tagRemovedEvent);
                break;
        }
    }

    private Responder newResponder(String channelId) {
        return (content, embed) -> this.messageApi.postMessage(
                UUID.fromString(channelId),
                new PostMessageRequest().content(content).embed(embed)
        );
    }

    private void respond(HttpExchange exchange, int status) throws IOException {
        exchange.sendResponseHeaders(status, -1);
        OutputStream out = exchange.getResponseBody();
        out.flush();
        out.close();
        exchange.close();
    }
}
