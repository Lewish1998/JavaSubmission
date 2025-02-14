package com.cos.models;

import java.nio.charset.Charset;
import java.util.Random;

public class Item {
    String sku;
    String name;
    double price;
    boolean onOffer;

    // Constructor
    public Item(String sku, String name, double price, boolean onOffer) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.onOffer = onOffer;
    }

    // Getters and setters 
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

    // public String createRandomSku() {
    //     byte[] array = new byte[12];
    //     new Random().nextBytes(array);
    //     String generatedString = new String(array, Charset.forName("UTF-8"));
    //     System.out.println(generatedString);
    //     this.sku = generatedString;
    //     return generatedString;
    // }
    
}
