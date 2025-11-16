package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller layer: mediates between the view (FXML) and the model.
 */
public class HelloController {

    @FXML
    private Button topicButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextField topicLabel =  new TextField("mytopic");
    private final HelloModel model = new HelloModel(new NtfyConnectionImpl(), topicLabel.getText());
    @FXML
    private ListView<NtfyMessageDto> messageView;
    @FXML
    private TextArea messageField;



    @FXML
    private void initialize() {
        messageView.setItems(model.getMessages());

        topicLabel.textProperty()
                .addListener((ov, t, t1) -> topicButton.setDisable(t1.isEmpty()));

        sendButton.setDisable(true);
        messageField.textProperty()
                .addListener((ov, t, t1) -> sendButton.setDisable(t1.isEmpty()));
    }

    public void sendMessage() {
        model.setMessageToSend(messageField.getText());
        messageField.setText("");
        model.sendMessage(topicLabel.getText());
        model.setMessageToSend("");
    }

    public void setTopic() {
        model.receiveMessage(topicLabel.getText());
        messageView.getItems().clear();
    }
}
