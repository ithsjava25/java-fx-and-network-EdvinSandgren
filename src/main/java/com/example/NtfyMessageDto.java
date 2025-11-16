package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NtfyMessageDto(String id, long time, String event, String topic, String message) {

    @Override
    public String toString() {
        Instant instant = Instant.ofEpochSecond(time);
        LocalDateTime localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " : " + message;
    }
}
