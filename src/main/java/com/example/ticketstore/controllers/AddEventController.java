package com.example.ticketstore.controllers;

import com.example.ticketstore.exceptions.EventAlreadyExistsException;
import com.example.ticketstore.utils.EventService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AddEventController {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField ticketNumberField;

    @FXML
    public void switchToAdmin(ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/com/example/ticketstore/fxmls/Admin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(homeRoot);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void saveButton(ActionEvent e) throws IOException {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));

        delay.setOnFinished(event -> {
            try {
                switchToAdmin(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        try {
            EventService.loadEventsFromDatabase();
            EventService.addEvent(titleField.getText(), artistField.getText(), dateField.getText(), Integer.parseInt(ticketNumberField.getText()));
            errorLabel.setText("Event added!");
            EventService.closeDatabase();
            delay.play();
        } catch (EventAlreadyExistsException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }

}
