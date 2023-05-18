package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userTextField;

    public void loginAsUser(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxmls/User.fxml"));
            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User Panel");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            thisStage.hide();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
