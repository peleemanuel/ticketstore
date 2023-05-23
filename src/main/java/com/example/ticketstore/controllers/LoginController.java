package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label lblError;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button btnSignIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent event) throws IOException {

    }

    public void cancel(ActionEvent event) throws IOException {

    }

    private void setLblError(Color color, String text) {
        lblError.setTextFill(color);
        lblError.setText(text);
        System.out.println(text);
    }

}
