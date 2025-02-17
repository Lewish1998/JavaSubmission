// package com.cos;

// import java.sql.*;

// import com.cos.daos.CustomerDao;
// import com.cos.daos.ItemDao;
// import com.cos.helpers.ConnectionManager;
// import com.cos.models.Customer;

// public class Main {
//     static String url = "jdbc:mysql://localhost:3306/new_schema";
//     static String user = "admin";
//     static String password = "admin";

//     public static void main(String[] args) {
//         CustomerDao cd = new CustomerDao();
//         ItemDao id = new ItemDao();

//         // String customerTable = "customers";
//         // dropTable(customerTable);

//         // Create customer table
//         executeSQL("""
//                     CREATE TABLE IF NOT EXISTS customers (
//                         id SERIAL PRIMARY KEY,
//                         fname VARCHAR(50) NOT NULL,
//                         lname VARCHAR(50) NOT NULL,
//                         email VARCHAR(100) UNIQUE NOT NULL
//                     );
//                 """);

//         // createCustomerTable(customerTable);

//         // Customer customer1 = new Customer("Lewis", "Halstead", "lewishalstead5@gmail.com");
//         // cd.save(customer1);

//         for (Customer customer : cd.getAll()) {
//             System.out.println(customer.getFname() + " " + customer.getLname());
//         }

//     }


//     public static void dropTable(String table) {
//         String sql = String.format("DROP TABLE IF EXISTS %s;", table);

//         try (Connection con = ConnectionManager.getConnection();
//              Statement stmt = con.createStatement()) {
//             stmt.execute(sql);
//             System.out.println("Table dropped.");
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }


//     public static void executeSQL(String sql) {
    
//             try (Connection con = ConnectionManager.getConnection();
//                  Statement stmt = con.createStatement()) {
    
//                 stmt.execute(sql);
//                 System.out.println("SQL executed successfully.");
    
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         }


//     public static void createCustomerTable(String table) {
//         // mysql code
//         // String sql = String.format("""
//         //             CREATE TABLE IF NOT EXISTS %s (
//         //                 id INT AUTO_INCREMENT PRIMARY KEY,
//         //                 fname VARCHAR(50) NOT NULL,
//         //                 lname VARCHAR(50) NOT NULL,
//         //                 email VARCHAR(100) UNIQUE NOT NULL
//         //             );
//         //         """, table);

//         // psql code
//         String sql = String.format("""
//                     CREATE TABLE IF NOT EXISTS %s (
//                         id SERIAL PRIMARY KEY,
//                         fname VARCHAR(50) NOT NULL,
//                         lname VARCHAR(50) NOT NULL,
//                         email VARCHAR(100) UNIQUE NOT NULL
//                     );
//                 """, table);

//         try (Connection con = ConnectionManager.getConnection();
//              Statement stmt = con.createStatement()) {

//             stmt.execute(sql);
//             System.out.println("Table created successfully.");

//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void addCustomer() {

//     }


// }