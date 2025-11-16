package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

/**
 * Controller layer: mediates between the view (FXML) and the model.
 */
public class HelloController {

    public TextField topicLabel =  new TextField("mytopic");
    private final HelloModel model = new HelloModel(new NtfyConnectionImpl(), topicLabel.getText());
    public ListView<NtfyMessageDto> messageView;
    public TextArea messageField;



    @FXML
    private void initialize() {
        messageView.setItems(model.getMessages());

    }

    public void sendMessage(ActionEvent actionEvent) {
        model.setMessageToSend(messageField.getText());
        messageField.setText(null);
        model.sendMessage(topicLabel.getText());
        model.setMessageToSend(null);
    }

    public void setTopic(ActionEvent actionEvent) {
        model.receiveMessage(topicLabel.getText());
        messageView.getItems().clear();
    }
}
