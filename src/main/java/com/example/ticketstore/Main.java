package com.example.ticketstore;

import com.example.ticketstore.utils.FileSystemService;
import com.example.ticketstore.utils.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.*; // removes unwanted slf4j warnings

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("fxmls/Main.fxml"));
            Image icon = new Image("file:src/main/resources/com/example/ticketstore/icons/person_icon.png"); // daca vreau sa mearga poza,
            stage.getIcons().add(icon);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}