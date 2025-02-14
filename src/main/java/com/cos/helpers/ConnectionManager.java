package com.cos.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    // private static String url = "jdbc:mysql://localhost:3306/new_schema"; // Local mysql db
    // TODO: move fields to env var
    private static String username = "admin";
    private static String password = "admin";

    private static String url = "jdbc:postgresql://ep-proud-unit-a9doizll-pooler.gwc.azure.neon.tech/neondb?user=neondb_owner&password=npg_DMqdm19OPksR&sslmode=require";
    private static Connection con;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url);//, username, password);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
            ex.printStackTrace(); // Print actual error message
        }
        return con;
    }

}