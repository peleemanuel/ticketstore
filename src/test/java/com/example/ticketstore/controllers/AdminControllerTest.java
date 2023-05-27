package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class AdminControllerTest {

    private AdminController controller;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxmls/Admin.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Stop
    public void stop() {
        // Clean up resources after each test
    }

    @Test
    public void testSwitchToSceneAdd(FxRobot robot) {
        // Click the switchToSceneAdd button
        robot.clickOn("#switchToSceneAddButton");
        verifyThat("#pageTitle", LabeledMatchers.hasText("Add Event"));
    }

    @Test
    public void testSwitchToSceneModify(FxRobot robot) {
        // Click the switchToSceneModify button
        robot.clickOn("#switchToSceneModifyButton");
        verifyThat("#pageTitle", LabeledMatchers.hasText("Modify Event"));
    }

    @Test
    public void testSwitchToSceneDelete(FxRobot robot) {
        // Click the switchToSceneDelete button
        robot.clickOn("#switchToSceneDeleteButton");
        verifyThat("#pageTitle", LabeledMatchers.hasText("Delete Event"));
    }

    @Test
    public void testSwitchToLogIn(FxRobot robot) {
        // Click the switchToLogIn button
        robot.clickOn("#switchToLogInButton");
        verifyThat("#pageTitle", LabeledMatchers.hasText("Login"));
    }
}
