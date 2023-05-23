module com.example.ticketstore {
    requires javafx.controls;
    requires javafx.fxml;

    requires nitrite;
    requires org.slf4j;

    exports com.example.ticketstore;
    opens com.example.ticketstore;
    exports com.example.ticketstore.controllers;
    opens com.example.ticketstore.controllers;
    exports com.example.ticketstore.models;
    opens com.example.ticketstore.models;
}