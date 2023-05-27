package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.utils.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class LoginControllerTest {

    private LoginController controller;

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("fxmls/Login.fxml"));
        primaryStage.setTitle("Login Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @BeforeEach
    void setUp() throws Exception {
        // UserService.loadUsersFromDatabase();
        FxToolkit.registerPrimaryStage();
        controller = new LoginController();


        // in loc sa punem direct, am putea sa facem metode pentru a returna atributele private (getter)
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.lblError = new Label();
    }


    @AfterEach
    @Disabled
    void tearDown() {
        UserService.closeDatabase();
    }

    @Test
    @DisplayName("Login as user")
    void testLoginInvalidUsername(FxRobot robot) throws IOException {
        // controller.usernameField.setText("invalid");
        // controller.passwordField.setText("invalid");
        // controller.login(null);
        // assertEquals("Username sau parola gresita!", controller.lblError.getText());
        robot.clickOn("#username");
        robot.write("User invalid");
        robot.clickOn("#password");
        robot.write("parola");
        robot.clickOn("#login");

        // assertEquals("Username sau parola gresita!", controller.getLblError());
        assertEquals(robot.lookup("#lblError").queryText(), LabeledMatchers.textMatcher("Expected Text"));
        // assertTrue(true);


    }
}
