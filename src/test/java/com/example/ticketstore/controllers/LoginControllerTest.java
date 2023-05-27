package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.utils.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class LoginControllerTest {


    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("fxmls/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
    }


    @AfterEach
    @Disabled
    void tearDown() {
        UserService.closeDatabase();
    }

    @Test
    @DisplayName("Trying to log in with a username that doesn't exist")
    void testLoginInvalidUsername(FxRobot robot) throws IOException {
        robot.clickOn("#username");
        robot.write("User invalid");
        robot.clickOn("#password");
        robot.write("parola");
        robot.clickOn("#login");
        verifyThat("#lblError", LabeledMatchers.hasText("Username sau parola gresita!"));
    }

    @Test
    @DisplayName("Logging in as an existing admin")
    void testLoginSuccess(FxRobot robot) throws IOException {
        robot.clickOn("#username");
        robot.write("admin");
        robot.clickOn("#password");
        robot.write("123");
        robot.clickOn("#login");
        verifyThat("#pageTitle", LabeledMatchers.hasText("Admin Panel"));
    }
}