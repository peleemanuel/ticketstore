package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png"); // daca vreau sa mearga poza,

    public void goToRegister(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/Register.fxml")));
            stage.setTitle("Register");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToLogin(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/Login.fxml")));
            stage.setTitle("Login");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
