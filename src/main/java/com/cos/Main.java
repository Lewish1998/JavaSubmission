package com.cos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cos.daos.CustomerDao;
import com.cos.daos.ItemDao;
import com.cos.daos.OrderDao;
import com.cos.daos.OrderItemDao;
import com.cos.models.Customer;
import com.cos.models.Item;
import com.cos.models.Order;
import com.cos.models.OrderItem;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner s = new Scanner(System.in);
        
        while (running) {
            options();
            String choice1 = s.nextLine();
            switch (choice1) {
                case "1":
                    customers(s);
                    break;
                case "2":
                    System.out.println("Not implimented");
                    break;
                case "3":
                    System.out.println("Not implimented");
                    break;
                case "4":
                    System.out.println("BYE THEN");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        s.close();
    }



    public static void options() {
        System.out.println("");
        System.out.println("1. Customers");
        System.out.println("2. Items");
        System.out.println("3. Orders");
        System.out.println("4 Exit");
        System.out.println(">>> ");
    }

    public static void customers(Scanner s) {
        System.out.println("");
        System.out.println("1. View all customers");
        System.out.println("2. Add new customer");
        System.out.println("3. TBC");
        System.out.println("4. Back");
        System.out.println(">>> ");
        String choice = s.nextLine();
        switch (choice) {
            case "1":
                viewAllCustomer();
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public static void viewAllCustomer() {
        System.out.println("");
        CustomerDao cd = new CustomerDao();
        List<Customer> x = cd.getAll();
        for (Customer i : x) {
            System.out.println("Customer " + i.getId() + ": " + i.getFname() + " " + i.getLname());
        }
    }






    public static void worker() {
         // Initialize DAOs
         CustomerDao cd = new CustomerDao();
         ItemDao id = new ItemDao();
         OrderDao od = new OrderDao();
         OrderItemDao oid = new OrderItemDao(); 
 
         // 1. Create and add a customer
         Customer customer1 = new Customer("Lewis", "Halstead", "lewishalstead5@gmail.com");
         cd.save(customer1);  // Save customer to database
 
         // 2. Create and add items
         Item item1 = new Item("1a", "apple", 0.75, false);
         id.save(item1);
         Item item2 = new Item("2b", "banana", 0.24, false);
         id.save(item2);
 
         // 3. Create order and add order items
         Order order1 = new Order(customer1);
         OrderItem orderItem1 = new OrderItem(item1, order1, 3, item1.getPrice());
         OrderItem orderItem2 = new OrderItem(item2, order1, 10,  item2.getPrice());
         order1.addOrderItem(orderItem1);
         order1.addOrderItem(orderItem2);
 
         // 4. Save the order
         od.save(order1);  // This will insert the order and retrieve its ID
 
         // 5. Print out order details
         System.out.println("Order ID: " + order1.getId());
         for (OrderItem oi : order1.getOrderItems()) {
             System.out.println("Item: " + oi.getItem().getName() + " Quantity: " + oi.getQuantity());
         }
    }
}
