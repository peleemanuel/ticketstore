package com.example.ticketstore.controllers;


import com.example.ticketstore.Main;
import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserPanelController implements Initializable {

    ArrayList<String> eventLists = new ArrayList<>();

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(),eventLists));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventService.loadEventsFromDatabase();
        List<Event> myList = EventService.getEvents();
        for (Event event : myList) {
            String tempFullName = event.getTitle() + ", " + event.getArtist() +  ", " + event.getData() ;
            eventLists.add(tempFullName);
            listView.getItems().add(tempFullName);
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
}
