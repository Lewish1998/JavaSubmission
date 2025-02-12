package com.cos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost:3306/new_schema";
    private static String username = "admin";
    private static String password = "admin";
    private static Connection con;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
            ex.printStackTrace(); // Print actual error message
        }
        return con;
    }

}