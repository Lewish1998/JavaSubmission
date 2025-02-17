package com.cos.models;

public class OrderItem {
    private long id;
    private Item item;
    private Order order;
    private int quantity;
    private double priceAtTime;

    // Constructor
    public OrderItem(Item item, Order order, int quantity, double priceAtTime) {
        this.item = item;
        this.order = order;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    public OrderItem(long id, Order order, Item item, int quantity, double priceAtTime) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    // Getters and setters
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(double priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public Order getOrder() {
        return order;
    }

    
    
}
