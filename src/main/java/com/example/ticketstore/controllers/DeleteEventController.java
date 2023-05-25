package com.example.ticketstore.controllers;

import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.EventService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteEventController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private ChoiceBox<String> eventChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventService.loadEventsFromDatabase();
        System.out.println("Am deschis pagina de delete");
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

    public void confirmDeleteEvent(ActionEvent e) {

        PauseTransition delay = new PauseTransition(Duration.seconds(1));

        delay.setOnFinished(event -> {
            try {
                switchToAdmin(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        try {
            String deletingEvent = eventChoiceBox.getValue();
            EventService.deleteEvent(deletingEvent);
            errorLabel.setText("Deleted succesfully");
            delay.play();
        } catch (Exception exception) {
            errorLabel.setText("Cannot delete");
            exception.printStackTrace();
        }

    }

}
