package com.example.ticketstore.controllers;


import com.example.ticketstore.Main;
import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.EventService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class UserPanelController implements Initializable {
    private List<Event> myList;
    private String touchedConcertName;
    private ArrayList<String> eventLists = new ArrayList<>();

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), eventLists));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventService.loadEventsFromDatabase();
        myList = EventService.getEvents();
        for (Event event : myList) {
            String tempFullName = event.getTitle() + ", " + event.getArtist() + ", " + event.getData();
            eventLists.add(tempFullName);
        }
        listView.getItems().addAll(eventLists);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

            }
        });
    }

    @FXML
    public void goToArtist(MouseEvent event) {

        try {
            EventService.closeDatabase();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ticketstore/fxmls/ConcertView.fxml"));
            Parent homeRoot = loader.load();
            touchedConcertName = listView.getSelectionModel().getSelectedItem();
            int commaIndex = touchedConcertName.indexOf(",");
            String extractedString = touchedConcertName.substring(0, commaIndex);

            for (Event events : myList) {
                if (events.getTitle().contains(extractedString)) {
                    ConcertViewController concertViewController = loader.getController();
                    concertViewController.setData(events);
                    break;
                }
            }


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(homeRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    public void goToHome(ActionEvent event) {
        EventService.closeDatabase();
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/Main.fxml")));
            stage.setTitle("Login");
            Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getItems() {
        return listView.getItems().toString();
    }
}
