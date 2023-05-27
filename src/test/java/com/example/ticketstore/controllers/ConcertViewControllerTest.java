package com.example.ticketstore.controllers;

import com.example.ticketstore.Main;
import com.example.ticketstore.models.Event;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
@ExtendWith(ApplicationExtension.class)
public class ConcertViewControllerTest  {

    private ConcertViewController controller;
    private Event event;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxmls/ConcertView.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        // Set up a dummy event
        event = new Event("Concert", "Artist", "2023-06-01", 100);
        controller.setData(event);

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
    public void testInitialize() {
        // Verify that the labels are correctly initialized with the event data
        verifyThat("#titleLabel", LabeledMatchers.hasText("Concert"));
        verifyThat("#artistLabel", LabeledMatchers.hasText("Artist"));
        verifyThat("#dataLabel", LabeledMatchers.hasText("2023-06-01"));
        verifyThat("#ticketNumberLabel", LabeledMatchers.hasText("100"));
    }

    @Test
    public void testBuyTicketsEnoughSpace(FxRobot robot) {
        // Set the ticket spinner value to 10
        robot.clickOn("#ticketSpinner");
        robot.write("10");
        robot.press(javafx.scene.input.KeyCode.ENTER);

        // Click the buyTickets button
        robot.clickOn("#buyTicketsButton");

        verifyThat("#errLabel", LabeledMatchers.hasText("Enough space, tickets bought"));

    }

    @Test
    public void testBuyTicketsNotEnoughSpace(FxRobot robot) {
        // Set the ticket spinner value to 200
        robot.clickOn("#ticketSpinner");
        robot.write("200");
        robot.press(javafx.scene.input.KeyCode.ENTER);

        // Click the buyTickets button
        robot.clickOn("#buyTicketsButton");

        verifyThat("#errLabel", LabeledMatchers.hasText("Not enough tickets remaining to fulfill order"));

    }
}
