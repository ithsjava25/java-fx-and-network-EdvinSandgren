package com.example;

import java.util.function.Consumer;

public class NtfyConnectionSpy implements NtfyConnection{

    String message;

    @Override
    public boolean send(String message, String topicLabel) {
        this.message = message;
        return true;
    }

    @Override
    public void receive(Consumer<NtfyMessageDto> messageHandler, String topicLabel) {
        NtfyMessageDto testMessage = new NtfyMessageDto("81dKXrFDQ4lZ", 1762434790, "message", topicLabel, "Hello World");
        messageHandler.accept(testMessage);
    }
}
