package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.exceptions.UserDoesNotExistException;
import com.example.ticketstore.models.User;
import com.example.ticketstore.utils.EventService;
import com.example.ticketstore.utils.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class LoginController {

    @FXML
    public Label lblError;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;

    public LoginController() {
    }

    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            setLblError(Color.BLACK, "Introduceti un username.");
            return;
        }
        if (password.isEmpty()) {
            setLblError(Color.BLACK, "Introduceti o parola.");
            return;
        }


        try {
            UserService.loadUsersFromDatabase();
            if (UserService.checkPassword(username, password) && UserService.checkUserExists(username)) {
                User currentUser = UserService.getUser(username);
                Scene scene = null;

                if (currentUser.getRole().equals("Client")) {

                    scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/User.fxml")));

                } else if (currentUser.getRole().equals("Admin")) {

                    scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/Admin.fxml")));

                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                stage.setTitle("User panel");
                Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png");
                stage.getIcons().add(icon);
                stage.setScene(scene);
                stage.show();


            } else {
                setLblError(Color.RED, "Username sau parola gresita!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            throw new RuntimeException(e);
        } finally {
            UserService.closeDatabase();
        }


    }

    public void cancel(ActionEvent event) throws IOException {
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

    private void setLblError(Color color, String text) {
        lblError.setTextFill(color);
        lblError.setText(text);
        System.out.println(text);
    }


}
