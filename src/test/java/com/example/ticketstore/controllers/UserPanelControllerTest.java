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
public class UserPanelControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("fxmls/User.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @BeforeEach
    void setUp() throws Exception {
        UserService.loadUsersFromDatabase();
        FxToolkit.registerPrimaryStage();
    }

    @AfterEach
    @Disabled
    void tearDown() {
        UserService.closeDatabase();
    }

    @Test
    @DisplayName("Sign out")
    void testSignOut(FxRobot robot) {
        // Welcome to Ticket Store!
        robot.clickOn("#logout");
    }

    @Test
    @DisplayName("Search an existing concert")
    void testSearchExistingConcert(FxRobot robot) {
        robot.clickOn("#searchBar");
        robot.write("Vine Delia");
        robot.clickOn("#search");

        ListView<String> listView = robot.lookup("#listView").queryListView();
        ObservableList<String> items = listView.getItems();
        verifyThat(items.get(0), LabeledMatchers.hasText("Vine Delia, Delia, 10.05.2023"));
    }

    @Test
    @DisplayName("Search a not existing concert")
    void testSearchNotExistingConcert(FxRobot robot) {
        robot.clickOn("#searchBar");
        robot.write("Concert care nu exista");
        robot.clickOn("#search");

        ListView<String> listView = robot.lookup("#listView").queryListView();
        ObservableList<String> items = listView.getItems();
        assertEquals(0, items.size());
    }

}
