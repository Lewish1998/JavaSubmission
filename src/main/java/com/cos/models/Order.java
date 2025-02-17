package com.cos.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private Customer customer;
    private List<OrderItem> orderItems;
    private String orderStatus;
    private boolean completed;

    // Constructor allowing empty orderItems initially
    public Order(Customer customer) {
        this.customer = customer;
        this.orderItems = new ArrayList<>();  // Initialize with an empty list of order items
        this.orderStatus = "Pending";  // Default status for new orders
        this.completed = false;  // Default to not completed
    }

    // Constructor that includes order items
    public Order(Customer customer, List<OrderItem> orderItems) {
        // this.id = id;
        this.customer = customer;
        this.orderItems = orderItems;
        this.orderStatus = "Pending";
        this.completed = false;
    }

    // Add a new order item to the list
    public void addOrderItem(OrderItem item) {
        this.orderItems.add(item);
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
