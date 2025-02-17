package com.cos.models;

public class Item {
    private long id; // database auto incriments 
    private String sku;
    private String name;
    private double price;
    private boolean onOffer;

    // Constructor without id (creation)
    public Item(String sku, String name, double price, boolean onOffer) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.onOffer = onOffer;
    }

    // Constructor with id (fetch)
    public Item(long id, String sku, String name, double price, boolean onOffer) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.onOffer = onOffer;
    }

    // Getters and setters 
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnOffer() {
        return onOffer;
    }

    public void setOnOffer(boolean onOffer) {
        this.onOffer = onOffer;
    }
    
}
