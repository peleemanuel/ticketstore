package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.EventService;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ConcertViewController implements Initializable {

    @FXML
    private Label errLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label ticketNumberLabel;
    @FXML
    private Spinner<Integer> ticketSpinner;

    private static int maxValue;
    int currentValue;
    public void setData(Event currentEvent) {
        titleLabel.setText(currentEvent.getTitle());
        artistLabel.setText(currentEvent.getArtist());
        dataLabel.setText(currentEvent.getData());
        ticketNumberLabel.setText(String.valueOf(( currentEvent.getTicketNumbers())));
        maxValue = currentEvent.getTicketNumbers();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventService.loadEventsFromDatabase();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,Integer.MAX_VALUE);
        System.out.println(maxValue);
        valueFactory.setValue(0);
        ticketSpinner.setValueFactory(valueFactory);
        currentValue = ticketSpinner.getValue();

        ticketSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentValue = ticketSpinner.getValue();
            }
        });

    }
    @FXML
    public void buyTickets(ActionEvent e){
        PauseTransition delay = new PauseTransition(Duration.seconds(1));

        delay.setOnFinished(event -> {
            goToHome(e);
        });

        if(EventService.verifyEnoughSpaceAtConcert(titleLabel.getText(),currentValue)) {
            EventService.buyTickets(titleLabel.getText(),currentValue);
            errLabel.setText("Enough space, tickets bought");
            delay.play();
        }
        else
            errLabel.setText("Not enough tickets remaining to fulfill order");
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
