package com.cos;

import java.sql.*;

public class Main {
    static String url = "jdbc:mysql://localhost:3306/new_schema";
    static String user = "admin";
    static String password = "admin";

    public static void main(String[] args) {
        String table = "customers";

//        dropTable(table);
//        createTable(table);

        CustomerDao cd = new CustomerDao();

        for (Customer customer : cd.getAll()) {
            System.out.println(customer.getFname());
        }

    }


    public static void dropTable(String table) {
        String sql = String.format("DROP TABLE IF EXISTS %s;", table);

        try (Connection con = ConnectionManager.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table dropped.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTable(String table) {
        String sql = String.format("""
                    CREATE TABLE IF NOT EXISTS %s (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        fname VARCHAR(50) NOT NULL,
                        lname VARCHAR(50) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL
                    );
                """, table);

        try (Connection con = ConnectionManager.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer() {

    }


}