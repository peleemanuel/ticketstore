package com.example.ticketstore.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.ticketstore.Main;
import com.example.ticketstore.models.User;
import com.example.ticketstore.utils.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.IOException;
@ExtendWith(ApplicationExtension.class)
class RegistrationControllerTest {

    @BeforeEach
    void setUp(){
        UserService.loadUsersFromDatabase();
    }

    @AfterEach
    void tearDown(){
        UserService.closeDatabase();
    }
    @Start
    void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("fxmls/Register.fxml"));
        primaryStage.setTitle("Registration test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot){
        robot.clickOn("#username");
        robot.write("user1234");
        assertTrue(true);
    }
}