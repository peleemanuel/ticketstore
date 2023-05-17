package com.example.ticketstore.utils;

import java.sql.*;

public class ConnectionUtil {
    Connection conn = null;

    public static Connection conDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost/keepto", "root", "root");
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}
