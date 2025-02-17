package com.cos.daos;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.cos.helpers.ConnectionManager;
import com.cos.models.Item;

public class ItemDao implements Dao<Item>{
    public ItemDao() {

    }

    @Override
    public Optional<Item> get(long id) { // Change id to name ?
        String sql = "SELECT * FROM items WHERE id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Item(
                    rs.getString("sku"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getBoolean("onOffer")
                ));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Connection con = ConnectionManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new Item(
                        rs.getString("sku"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getBoolean("on_offer")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }


    @Override
    public void save(Item item) {
        String sql = "INSERT INTO items (sku, name, price, on_offer) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getSku());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getPrice());
            stmt.setBoolean(4, item.isOnOffer());
            
            stmt.executeUpdate();
            
            // Retrieve and set the generated ID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long generatedId = rs.getLong(1);
                item.setId(generatedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void update(Item item, Object[] params) {
        if (params.length < 4) {
            throw new IllegalArgumentException("Params must include sku, name, price and onOffer");
        }

        String sql = "UPDATE items SET sku = ?, name = ?, price = ?, on_offer = ? WHERE id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, (String) params[0]);
            stmt.setString(2, (String) params[1]);
            stmt.setDouble(3, (Double) params[2]);
            stmt.setBoolean(4, (Boolean) params[3]);
            stmt.setLong(5, item.getId());
            // stmt.setString(5, item.getSku());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Item item) {
        String sql = "DELETE FROM items WHERE id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, item.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //     public String createRandomSku() {
    //     byte[] array = new byte[12];
    //     new Random().nextBytes(array);
    //     String generatedString = new String(array, Charset.forName("UTF-8"));
    //     System.out.println(generatedString);
    //     // this.sku = generatedString;
    //     return generatedString;
    // }
}
