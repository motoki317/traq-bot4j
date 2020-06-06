package com.github.motoki317.traq_bot;

import com.github.motoki317.traq_bot.model.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EventHandlers {
    private Consumer<PingEvent> pingHandler;
    private BiConsumer<JoinedEvent, Responder> joinedHandler;
    private BiConsumer<LeftEvent, Responder> leftHandler;
    private BiConsumer<MessageCreatedEvent, Responder> messageCreatedHandler;
    private BiConsumer<DirectMessageCreatedEvent, Responder> directMessageCreatedHandler;
    private BiConsumer<ChannelCreatedEvent, Responder> channelCreatedHandler;
    private BiConsumer<ChannelTopicChangedEvent, Responder> channelTopicChangedHandler;
    private Consumer<UserCreatedEvent> userCreatedHandler;
    private Consumer<StampCreatedEvent> stampCreatedHandler;
    private Consumer<TagAddedEvent> tagAddedHandler;
    private Consumer<TagRemovedEvent> tagRemovedHandler;

    public EventHandlers() {
        this.pingHandler = e -> {};
        this.joinedHandler = (e, r) -> {};
        this.leftHandler = (e, r) -> {};
        this.messageCreatedHandler = (e, r) -> {};
        this.directMessageCreatedHandler = (e, r) -> {};
        this.channelCreatedHandler = (e, r) -> {};
        this.channelTopicChangedHandler = (e, r) -> {};
        this.userCreatedHandler = e -> {};
        this.stampCreatedHandler = e -> {};
        this.tagAddedHandler = e -> {};
        this.tagRemovedHandler = e -> {};
    }

    public void setPingHandler(Consumer<PingEvent> pingHandler) {
        this.pingHandler = pingHandler;
    }

    public void setJoinedHandler(BiConsumer<JoinedEvent, Responder> joinedHandler) {
        this.joinedHandler = joinedHandler;
    }

    public void setLeftHandler(BiConsumer<LeftEvent, Responder> leftHandler) {
        this.leftHandler = leftHandler;
    }

    public void setMessageCreatedHandler(BiConsumer<MessageCreatedEvent, Responder> messageCreatedHandler) {
        this.messageCreatedHandler = messageCreatedHandler;
    }

    public void setDirectMessageCreatedHandler(BiConsumer<DirectMessageCreatedEvent, Responder> directMessageCreatedHandler) {
        this.directMessageCreatedHandler = directMessageCreatedHandler;
    }

    public void setChannelCreatedHandler(BiConsumer<ChannelCreatedEvent, Responder> channelCreatedHandler) {
        this.channelCreatedHandler = channelCreatedHandler;
    }

    public void setChannelTopicChangedHandler(BiConsumer<ChannelTopicChangedEvent, Responder> channelTopicChangedHandler) {
        this.channelTopicChangedHandler = channelTopicChangedHandler;
    }

    public void setUserCreatedHandler(Consumer<UserCreatedEvent> userCreatedHandler) {
        this.userCreatedHandler = userCreatedHandler;
    }

    public void setStampCreatedHandler(Consumer<StampCreatedEvent> stampCreatedHandler) {
        this.stampCreatedHandler = stampCreatedHandler;
    }

    public void setTagAddedHandler(Consumer<TagAddedEvent> tagAddedHandler) {
        this.tagAddedHandler = tagAddedHandler;
    }

    public void setTagRemovedHandler(Consumer<TagRemovedEvent> tagRemovedHandler) {
        this.tagRemovedHandler = tagRemovedHandler;
    }

    void handlePing(PingEvent event) {
        this.pingHandler.accept(event);
    }

    void handleJoined(JoinedEvent event, Responder responder) {
        this.joinedHandler.accept(event, responder);
    }

    void handleLeft(LeftEvent event, Responder responder) {
        this.leftHandler.accept(event, responder);
    }

    void handleMessageCreated(MessageCreatedEvent event, Responder responder) {
        this.messageCreatedHandler.accept(event, responder);
    }

    void handleDirectMessageCreated(DirectMessageCreatedEvent event, Responder responder) {
        this.directMessageCreatedHandler.accept(event, responder);
    }

    void handleChannelCreated(ChannelCreatedEvent event, Responder responder) {
        this.channelCreatedHandler.accept(event, responder);
    }

    void handleChannelTopicChanged(ChannelTopicChangedEvent event, Responder responder) {
        this.channelTopicChangedHandler.accept(event, responder);
    }

    void handleUserCreated(UserCreatedEvent event) {
        this.userCreatedHandler.accept(event);
    }

    void handleStampCreated(StampCreatedEvent event) {
        this.stampCreatedHandler.accept(event);
    }

    void handleTagAdded(TagAddedEvent event) {
        this.tagAddedHandler.accept(event);
    }

    void handleTagRemoved(TagRemovedEvent event) {
        this.tagRemovedHandler.accept(event);
    }
}
