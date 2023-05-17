module com.example.ticketstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.ticketstore to javafx.fxml;
    exports com.example.ticketstore;
    exports com.example.ticketstore.controllers;
    opens com.example.ticketstore.controllers;
}