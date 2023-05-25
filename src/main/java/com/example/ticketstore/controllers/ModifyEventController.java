package com.example.ticketstore.controllers;

import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyEventController implements Initializable {

    @FXML
    private ChoiceBox<String> eventChoiceBox;

    @FXML
    private TextField artistField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField ticketNumbersField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventService.loadEventsFromDatabase();
        List<Event> myList = EventService.getEvents();
        for (Event event : myList) {
            eventChoiceBox.getItems().add(event.getTitle());
        }
    }

    @FXML
    public void switchToAdmin(ActionEvent event) throws IOException {
        EventService.closeDatabase();
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/com/example/ticketstore/fxmls/Admin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(homeRoot);
        stage.setScene(scene);
        stage.show();
    }
}
