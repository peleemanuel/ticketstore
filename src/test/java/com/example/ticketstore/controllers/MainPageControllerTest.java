package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.utils.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
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
public class MainPageControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("fxmls/Main.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    @DisplayName("Select register button")
    void testSelectRegister(FxRobot robot) {
        robot.clickOn("#register");
        verifyThat("#title-text", LabeledMatchers.hasText("FIS - Student Manager"));
    }

    @Test
    @DisplayName("Select login button")
    void testSelectLogin(FxRobot robot) {
        robot.clickOn("#login");
        verifyThat("#pageTitle", LabeledMatchers.hasText("Login"));
    }
}
