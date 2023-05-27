package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.exceptions.EmptyUsernameOrPasswordException;
import com.example.ticketstore.exceptions.UsernameAlreadyExistsException;
import com.example.ticketstore.utils.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Label registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox<String> role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.loadUsersFromDatabase();
            UserService.addUser(usernameField.getText(), passwordField.getText(), role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (EmptyUsernameOrPasswordException e) {
            throw new RuntimeException(e);
        } finally {
            UserService.closeDatabase();
        }
    }

    public void goToHome(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/Main.fxml")));
            stage.setTitle("Login");
            Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png"); // daca vreau sa mearga poza,
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRegistrationMessage() {
        return registrationMessage.getText();
    }

}