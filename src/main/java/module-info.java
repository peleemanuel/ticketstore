module com.example.ticketstore {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.example.ticketstore;
    opens com.example.ticketstore;
    exports com.example.ticketstore.controllers;
    opens com.example.ticketstore.controllers;
}