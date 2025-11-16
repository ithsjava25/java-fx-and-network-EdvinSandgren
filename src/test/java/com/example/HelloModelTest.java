package com.example;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.application.Platform;


@WireMockTest
class HelloModelTest {
    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {
        });
    }

    @Test
    @DisplayName("Given a model with messageToSend when calling sendMessage then send method on connection should be called")
    void sendMessageCallsConnectionWithMessageToSend() {
        //Arrange  Given
        var spy = new NtfyConnectionSpy();
        var model = new HelloModel(spy, "mytopic");
        model.setMessageToSend("Hello World");
        //Act  When
        model.sendMessage("mytopic");
        //Assert   Then
        assertThat(spy.message).isEqualTo("Hello World");
    }

    @Test
    void sendMessageToFakeServer(WireMockRuntimeInfo wmRuntimeInfo) {
        var con = new NtfyConnectionImpl("http://localhost:" + wmRuntimeInfo.getHttpPort());
        var model = new HelloModel(con, "mytopic");
        model.setMessageToSend("Hello World");
        stubFor(post("/mytopic").willReturn(ok()));

        model.sendMessage("mytopic");

        //Verify call made to server
        verify(postRequestedFor(urlEqualTo("/mytopic"))
                .withRequestBody(matching("Hello World")));
    }

    @Test
    void receiveMessageFromFakeServer(WireMockRuntimeInfo wmRuntimeInfo) {
        var spy = new NtfyConnectionSpy();
        var model = new HelloModel(spy, "mytopic");

        model.receiveMessage("mytopic");

        assertThat(model.getMessages()).extracting(NtfyMessageDto::message).contains("Hello World");

    }
}