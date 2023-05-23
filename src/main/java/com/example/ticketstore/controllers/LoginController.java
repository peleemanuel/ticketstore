package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.exceptions.UserDoesNotExistException;
import com.example.ticketstore.models.User;
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

public class LoginController implements Initializable {

    @FXML
    private Label lblError;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ChoiceBox<String> role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Client", "Admin");
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
        if (role.getValue() == null) {
            setLblError(Color.BLACK, "Introduceti un rol");
            return;
        }

        try {
            UserService.loadUsersFromDatabase();
            if (UserService.checkPassword(username, password) && UserService.checkUserExists(username)) {
                if (role.getValue().equals("Client")) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/User.fxml")));
                    stage.setTitle("User panel");
                    Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png");
                    stage.getIcons().add(icon);
                    stage.setScene(scene);
                    stage.show();
                } else if (role.getValue().equals("Admin")) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/Admin.fxml")));
                    stage.setTitle("User panel");
                    Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png");
                    stage.getIcons().add(icon);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    setLblError(Color.RED, "Username sau parola gresita!");
                }
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

    }

    private void setLblError(Color color, String text) {
        lblError.setTextFill(color);
        lblError.setText(text);
        System.out.println(text);
    }

}
