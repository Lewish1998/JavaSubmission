package com.cos.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.cos.helpers.ConnectionManager;
import com.cos.models.Order;
import com.cos.models.OrderItem;

public class OrderItemDao implements Dao<OrderItem> {
    public OrderItemDao() {

    }

    @Override
    public Optional<OrderItem> get(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<OrderItem> getAll() {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }






    @Override
    public void save(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, item_id, quantity, price_at_time) VALUES (?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Ensure that order_id (from the Order object) and item_id (from the Item object) are valid
            stmt.setLong(1, orderItem.getOrder().getId());  // The order's id
            stmt.setLong(2, orderItem.getItem().getId());   // The item's id
            stmt.setInt(3, orderItem.getQuantity());        // Quantity
            stmt.setDouble(4, orderItem.getPriceAtTime());  // Price at time

            // Execute the insertion into order_items
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    // @Override
    // public void save(OrderItem t) {
    //     throw new UnsupportedOperationException("Unimplemented method 'save'");
    // }

    @Override
    public void update(OrderItem t, Object[] params) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(OrderItem t) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    
}
