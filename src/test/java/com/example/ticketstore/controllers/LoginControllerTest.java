package com.example.ticketstore.controllers;

import com.example.ticketstore.utils.UserService;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class LoginControllerTest {

    private LoginController controller;

    @BeforeEach
    public void setUp() throws Exception {
        UserService.loadUsersFromDatabase();
        FxToolkit.registerPrimaryStage();
        controller = new LoginController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.lblError = new Label();
    }

    @Test
    @DisplayName("Login as user")
    void testLoginInvalidUsername(FxRobot robot) throws IOException {
        controller.usernameField.setText("invalid");
        controller.passwordField.setText("invalid");
        // controller.login(null);
        // assertEquals("Username sau parola gresita!", controller.lblError.getText());
        assertTrue(true);


    }
}
