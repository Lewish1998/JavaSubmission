package com.cos.models;

public class OrderItem {
    private Item item;
    private int quantity;
    private double priceAtTime;

    // Constructor
    public OrderItem(Item item, int quantity, double priceAtTime) {
        this.item = item;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    // Getters and setters
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

    
    
}
