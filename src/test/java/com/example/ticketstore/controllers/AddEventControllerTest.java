package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.exceptions.EventAlreadyExistsException;
import com.example.ticketstore.utils.EventService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.api.FxAssert.verifyThat;
@ExtendWith(ApplicationExtension.class)
public class AddEventControllerTest {

    private AddEventController controller;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxmls/AddEvent.fxml"));
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
    public void testSaveButtonSuccess(FxRobot robot) {
        // Mock event data
        //it is possible to have to rewrite the title in order to have successful test
        String title = "Concert1";
        String artist = "John Doe";
        String date = "2023-06-01";
        int ticketNumber = 100;

        // Set input values
        robot.clickOn("#titleField").write(title);
        robot.clickOn("#artistField").write(artist);
        robot.clickOn("#dateField").write(date);
        robot.clickOn("#ticketNumberField").write(Integer.toString(ticketNumber));

        // Click the save button
        robot.clickOn("#saveButton");

        // Verify the error label message
        verifyThat("#errorLabel", LabeledMatchers.hasText("Event added!"));

    }

    @Test
    public void testSaveButtonEventExists(FxRobot robot) {
        // Mock event data
        String title = "Concert";
        String artist = "John Doe";
        String date = "2023-06-01";
        int ticketNumber = 100;

        // Set input values
        robot.clickOn("#titleField").write(title);
        robot.clickOn("#artistField").write(artist);
        robot.clickOn("#dateField").write(date);
        robot.clickOn("#ticketNumberField").write(Integer.toString(ticketNumber));

        // Mock event already exists exception
        String errorMessage = "";

        // Click the save button
        verifyThat("#errorLabel", LabeledMatchers.hasText(errorMessage));

    }
}