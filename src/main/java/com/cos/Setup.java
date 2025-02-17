package com.cos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cos.daos.CustomerDao;
import com.cos.daos.ItemDao;
import com.cos.daos.OrderDao;
import com.cos.daos.OrderItemDao;
import com.cos.helpers.ConnectionManager;
import com.cos.models.Customer;
import com.cos.models.Item;
import com.cos.models.Order;
import com.cos.models.OrderItem;

public class Setup {
    static String url = "jdbc:mysql://localhost:3306/new_schema";
    static String user = "admin";
    static String password = "admin";

    public static void main(String[] args) {
        CustomerDao cd = new CustomerDao();
        ItemDao id = new ItemDao();
        OrderDao od = new OrderDao();
        OrderItemDao oid = new OrderItemDao();

        // # # # # # # # # # # # # # # # customers # # # # # # # # # # # # # # # 
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

        System.out.println();

        // Delete customer
        cd.delete(customer1);
        for (Customer customer : cd.getAll()) {
            System.out.println(customer.getFname() + " " + customer.getLname());
        }

        System.out.println();

        // Update customer
        String[] test = {"test", "test", "test"};
        cd.update(customer5, test);
        for (Customer customer : cd.getAll()) {
            System.out.println(customer.getFname() + " " + customer.getLname() + " " + customer.getEmail());
        }


        // # # # # # # # # # # # # # # # items # # # # # # # # # # # # # # # 
        // Create and add items
        dropTable("items");

        // // Create item table
        executeSQL("""
                    CREATE TABLE IF NOT EXISTS items (
                        id SERIAL PRIMARY KEY,
                        sku VARCHAR(50) NOT NULL UNIQUE,
                        name VARCHAR(50) NOT NULL,
                        price NUMERIC(10, 2) NOT NULL,
                        on_offer BOOLEAN NOT NULL
                    );
                """);
        
        Item item1 = new Item("1a", "apple", 0.75, false);
        id.save(item1);
        Item item2 = new Item("2b", "banana", 0.24, false);
        id.save(item2);
        Item item3 = new Item("3c", "pear", 0.99, false);
        id.save(item3);
        Item item4 = new Item("4d", "orange", 1.11, false);
        id.save(item4);
        Item item5 = new Item("5e", "melon", 1.35, false);
        id.save(item5);

        for (Item item : id.getAll()) {
            System.out.println(item.getSku() + "\n" + item.getName() + "\n" + item.getPrice() + "\n" + item.isOnOffer() + "\n");
        }

        System.out.println();

        // Delete item
        id.delete(item1);
        for (Item item : id.getAll()) {
            System.out.println(item.getSku() + "\n" + item.getName() + "\n" + item.getPrice() + "\n" + item.isOnOffer() + "\n");
        }

        System.out.println();

        // Update item
        Object[] test2 = {"test", "test", 99.99, true};
        id.update(item5, test2);
        for (Item item : id.getAll()) {
            System.out.println(item.getSku() + " " + item.getName() + " " + item.getPrice() + " " + item.isOnOffer());
        }









        // # # # # # # # # # # # # # # # orders # # # # # # # # # # # # # # # 
        Customer customer6 = new Customer("xxx", "xxx", "xxx@gmail.com");
        cd.save(customer6);
        System.out.println(customer6.getId());
        // Drop order table
        dropTable("orders");
        
        // Create order table
        executeSQL("""
                    CREATE TABLE IF NOT EXISTS orders (
                        id SERIAL PRIMARY KEY,
                        customer_id INTEGER,
                        created_at TIMESTAMP,
                        in_progress BOOLEAN,
                        order_status VARCHAR(100),
                        completed BOOLEAN,
                        FOREIGN KEY (customer_id) REFERENCES customers(id)
                    );
                """);

        OrderItem orderItem1 = new OrderItem(item1, 3, 0.75); // 3 apples at $0.75 each
        OrderItem orderItem2 = new OrderItem(item2, 5, 0.50); // 5 bananas at $0.50 each

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        Order order1 = new Order(customer6, orderItems);

        od.save(order1);





        // # # # # # # # # # # # # # # # order_items # # # # # # # # # # # # # # # 
        // Drop order_items table
        dropTable("order_items");

        // Create order_items table
        executeSQL("""
                    CREATE TABLE order_items (
                        id SERIAL PRIMARY KEY,
                        order_id INTEGER,
                        item_id INTEGER,
                        quantity INTEGER,
                        price_at_time FLOAT,
                        UNIQUE (order_id, item_id),
                        -- PRIMARY KEY (order_id, item_id),
                        FOREIGN KEY (order_id) REFERENCES orders(id),
                        FOREIGN KEY (item_id) REFERENCES items(id)
                    );
                """);

        od.save(order1);

    }








    // # # # # # # # # # # # # # # # methods # # # # # # # # # # # # # # # 


    public static void dropTable(String table) {
        String sql = String.format("DROP TABLE IF EXISTS %s CASCADE;", table);
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


}