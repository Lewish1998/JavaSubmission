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

public class OrderDao implements Dao<Order> {
    public OrderDao() {

    }

    @Override
    public Optional<Order> get(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void save(Order order) {
        String sqlOrder = "INSERT INTO orders (customer_id, order_status, completed) VALUES (?, ?, ?)";
        
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement stmtOrder = con.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmtOrder.setLong(1, order.getCustomer().getId());
            stmtOrder.setString(2, order.getOrderStatus());
            stmtOrder.setBoolean(3, order.isCompleted());
            
            stmtOrder.executeUpdate();
            
            ResultSet rs = stmtOrder.getGeneratedKeys();
            if (rs.next()) {
                long orderId = rs.getLong(1);

                String sqlOrderItem = "INSERT INTO order_items (order_id, item_id, quantity, price_at_time) VALUES (?, ?, ?, ?)";

                try (PreparedStatement stmtOrderItem = con.prepareStatement(sqlOrderItem)) {
                    for (OrderItem orderItem : order.getOrderItems()) {
                        stmtOrderItem.setLong(1, orderId);
                        stmtOrderItem.setLong(2, orderItem.getItem().getId());
                        stmtOrderItem.setInt(3, orderItem.getQuantity());
                        stmtOrderItem.setDouble(4, orderItem.getPriceAtTime());
                        stmtOrderItem.addBatch();
                    }
                    order.setId(rs.getLong(1));
                    stmtOrderItem.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Order order, Object[] params) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Order order) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
}
