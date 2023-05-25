package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.ConcertRetain;
import com.example.ticketstore.utils.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConcertViewController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label ticketNumberLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventService.loadEventsFromDatabase();
        Event currentEvent = EventService.getEvent(ConcertRetain.getConcertName());
        titleLabel.setText(currentEvent.getTitle());
        artistLabel.setText(currentEvent.getArtist());
        dataLabel.setText(currentEvent.getData());
        ticketNumberLabel.setText(String.valueOf(( currentEvent.getTicketNumbers())));
    }

    public void goToHome(ActionEvent event) {
        EventService.closeDatabase();
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/User.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
