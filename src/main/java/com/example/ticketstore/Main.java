package com.example.ticketstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));

            Image admin_icon = new Image("file:src/main/resources/com/example/ticketstore/admin_panel.png"); // daca vreau sa mearga poza,
            stage.getIcons().add(admin_icon);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("AdminPanel");
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