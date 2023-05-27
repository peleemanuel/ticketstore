package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.models.Event;
import com.example.ticketstore.utils.EventService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class DeleteEventControllerTest {

    private DeleteEventController controller;
    private Event event;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxmls/DeleteEvent.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Stop
    public void stop() {
        // Clean up resources after each test
        EventService.closeDatabase();
    }

    @Test
    public void testInitialize(FxRobot robot) {
        // Verify that the choice box is populated with event titles
        assertEquals(3, robot.lookup("#eventChoiceBox").queryAs(ChoiceBox.class).getItems().size());
        // Add more assertions or verifications if needed
    }

    @Test
    public void testConfirmDeleteEvent(FxRobot robot) {
        // Select "Event 1" in the choice box
        robot.clickOn("#eventChoiceBox");
        robot.type(KeyCode.ENTER);

        // Click the confirmDeleteEvent button
        robot.clickOn("#confirmDeleteEventButton");

        // Verify that the error label shows the appropriate message
        verifyThat("#errorLabel", LabeledMatchers.hasText("Deleted successfully"));


    }
}
