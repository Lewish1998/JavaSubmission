package com.cos.daos;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void save(Item item) {
        String sql = "INSERT INTO items (sku, name, price, on_sale) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, item.getSku());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getPrice());
            stmt.setBoolean(3, item.isOnOffer());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Item t, String[] params) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Item t) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

        public String createRandomSku() {
        byte[] array = new byte[12];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        System.out.println(generatedString);
        // this.sku = generatedString;
        return generatedString;
    }
}
