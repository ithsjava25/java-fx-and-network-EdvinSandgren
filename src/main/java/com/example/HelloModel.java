package com.example;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.http.HttpClient;
import java.util.Objects;



public class HelloModel {

    private final NtfyConnection connection;
    private final ObservableList<NtfyMessageDto> messages = FXCollections.observableArrayList();
    private final StringProperty messageToSend = new SimpleStringProperty();

    public  HelloModel(NtfyConnection connection) {
        Dotenv dotenv = Dotenv.load();
        hostName = Objects.requireNonNull(dotenv.get("HOST_NAME"));
        receiveMessage();
        this.connection = connection;
    }

    private void receiveMessage() {

    }

    public String getGreeting() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        return "Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".";
    }

    public void sendMessage() {
        connection.send(messageToSend.get());
    }

    public void setMessageToSend(String messageToSend) {
        this.messageToSend.set(messageToSend);
    }
}
