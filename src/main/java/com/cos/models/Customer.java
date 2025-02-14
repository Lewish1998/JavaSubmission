package com.cos.models;

public class Customer {

    String fname;
    String lname;
    String email;

    // Constructor
    public Customer(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public Customer(String string, String string2, double double1, boolean boolean1) {
        //TODO Auto-generated constructor stub
    }

    // Getters and setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
