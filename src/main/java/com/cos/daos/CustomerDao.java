package com.cos.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cos.helpers.ConnectionManager;
import com.cos.models.Customer;

public class CustomerDao implements Dao<Customer> {

    public CustomerDao() {
        
    }

    @Override
    public Optional<Customer> get(long id) { // Change id to name ?
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Customer(
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection con = ConnectionManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers (fname, lname, email) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, customer.getFname());
            stmt.setString(2, customer.getLname());
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer, String[] params) {
        if (params.length < 2) {
            throw new IllegalArgumentException("Params must include fname and email");
        }

        String sql = "UPDATE customers SET fname = ?, email = ? WHERE email = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, params[0]);
            stmt.setString(2, params[1]);
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        String sql = "DELETE FROM customers WHERE email = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, customer.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



















//package com.cos;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//public class CustomerDao implements Dao<Customer> {
//
//    private List<Customer> customers = new ArrayList<>();
//
//    public CustomerDao() {
//        customers.add(new Customer("John", "Smith", "john@domain.com"));
//        customers.add(new Customer("Susan", "James", "susan@domain.com"));
//    }
//
//    @Override
//    public Optional<Customer> get(long id) {
//        return Optional.ofNullable(customers.get((int) id));
//    }
//
//    @Override
//    public List<Customer> getAll() {
//        return customers;
//    }
//
//    @Override
//    public void save(Customer customer) {
//        customers.add(customer);
//    }
//
//    @Override
//    public void update(Customer customer, String[] params) {
//        customer.setFname(Objects.requireNonNull(
//                params[0], "Name cannot be null"));
//        customer.setEmail(Objects.requireNonNull(
//                params[1], "Email cannot be null"));
//
//        customers.add(customer);
//    }
//
//    @Override
//    public void delete(Customer customer) {
//        customers.remove(customer);
//    }
//}