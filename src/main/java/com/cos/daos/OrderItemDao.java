package com.cos.daos;

import java.util.List;
import java.util.Optional;

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
    public void save(OrderItem t) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(OrderItem t, Object[] params) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(OrderItem t) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    
}
