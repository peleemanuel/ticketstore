package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class RegistrationControllerTest {

    private RegistrationController controller;

    @Start
    void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxmls/Register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        controller = new RegistrationController();
    }

    @Test
    public void testRegistrationSuccess(FxRobot robot) {
        //it is possible to have to rewrite the username
        robot.clickOn("#usernameField").write("testuser7");
        robot.clickOn("#passwordField").write("password");
        robot.clickOn("#role").clickOn("Client");
        robot.clickOn("#registerButton");

        verifyThat("#registrationMessage", LabeledMatchers.hasText("Account created successfully!"));
    }

    @Test
    public void testRegistrationUsernameExists(FxRobot robot) {
        robot.clickOn("#usernameField").write("admin");
        robot.clickOn("#passwordField").write("123");
        robot.clickOn("#role").clickOn("Admin");
        robot.clickOn("#registerButton");

        String expectedMessage = "An account with the username admin already exists!";
        verifyThat("#registrationMessage", LabeledMatchers.hasText(expectedMessage));
    }
}
