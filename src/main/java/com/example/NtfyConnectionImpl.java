package com.example;

import java.net.http.HttpClient;
import java.util.function.Consumer;

public class NtfyConnectionImpl implements NtfyConnection {

    private final String hostName;
    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean send(String message) {
        return false;
    }

    @Override
    public void receive(Consumer<NtfyMessageDto> messageHandler) {

    }
}
