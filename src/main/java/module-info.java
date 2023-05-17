module com.example.ticketstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.ticketstore;
    opens com.example.ticketstore;
}