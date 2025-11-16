package com.example;

import java.util.function.Consumer;

public interface NtfyConnection {

    public boolean send(String message, String topicLabel);

    public void receive(Consumer<NtfyMessageDto> messageHandler, String topicLabel);

}
