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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    private void setLblError(Color color, String text) {
        lblError.setTextFill(color);
        lblError.setText(text);
        System.out.println(text);
    }

    private String logIn() {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String status = "Success";
        String email = userTextField.getText();
        String password = passwordTextField.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM admins Where email = ? and password = ?";
            try {
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketstore", "root", "root");
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        return status;
    }

    public void loginAsUser(ActionEvent event) throws IOException {


        if (event.getSource() == btnSignIn) {
            if (logIn().equals("Success")) {
                try {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("fxmls/User.fxml")));
                    stage.setTitle("User Panel");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
