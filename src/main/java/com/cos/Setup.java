package com.cos;

import java.sql.*;

import com.cos.daos.CustomerDao;
import com.cos.daos.ItemDao;
import com.cos.helpers.ConnectionManager;
import com.cos.models.Customer;
import com.cos.models.Item;

public class Setup {
    static String url = "jdbc:mysql://localhost:3306/new_schema";
    static String user = "admin";
    static String password = "admin";

    public static void main(String[] args) {
        CustomerDao cd = new CustomerDao();
        ItemDao id = new ItemDao();

        // String customerTable = "customers";
        dropTable("customers");

        // Create customer table
        executeSQL("""
                    CREATE TABLE IF NOT EXISTS customers (
                        id SERIAL PRIMARY KEY,
                        fname VARCHAR(50) NOT NULL,
                        lname VARCHAR(50) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL
                    );
                """);

        // createCustomerTable(customerTable);

        // Create and add customers
        Customer customer1 = new Customer("Lewis", "Halstead", "lewishalstead5@gmail.com");
        cd.save(customer1);
        Customer customer2 = new Customer("Steve", "Jobs", "stevejobs@gmail.com");
        cd.save(customer2);
        Customer customer3 = new Customer("Jane", "Doe", "janedoe@gmail.com");
        cd.save(customer3);
        Customer customer4 = new Customer("Bob", "Ross", "bobross@gmail.com");
        cd.save(customer4);
        Customer customer5 = new Customer("Andy", "Murray", "andymurray@gmail.com");
        cd.save(customer5);

        for (Customer customer : cd.getAll()) {
            System.out.println(customer.getFname() + " " + customer.getLname());
        }


        // Create and add items
        dropTable("items");

        // Create customer table
        executeSQL("""
                    CREATE TABLE IF NOT EXISTS items (
                        id SERIAL PRIMARY KEY,
                        sku VARCHAR(50) NOT NULL UNIQUE,
                        name VARCHAR(50) NOT NULL,
                        price NUMERIC(6, 2) NOT NULL,
                        on_offer BOOLEAN NOT NULL
                    );
                """);
        Item item1 = new Item(id.createRandomSku(), "apple", 0.75, false);
        id.save(item1);

        for (Item item : id.getAll()) {
            System.out.println(item.getSku() + "\n" + item.getName() + "\n" + item.getPrice() + "\n" + item.isOnOffer() + "\n");
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


    public static void executeSQL(String sql) {
    
            try (Connection con = ConnectionManager.getConnection();
                 Statement stmt = con.createStatement()) {
    
                stmt.execute(sql);
                System.out.println("SQL executed successfully.");
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public static void createCustomerTable(String table) {
        // mysql code
        // String sql = String.format("""
        //             CREATE TABLE IF NOT EXISTS %s (
        //                 id INT AUTO_INCREMENT PRIMARY KEY,
        //                 fname VARCHAR(50) NOT NULL,
        //                 lname VARCHAR(50) NOT NULL,
        //                 email VARCHAR(100) UNIQUE NOT NULL
        //             );
        //         """, table);

        // psql code
        String sql = String.format("""
                    CREATE TABLE IF NOT EXISTS %s (
                        id SERIAL PRIMARY KEY,
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