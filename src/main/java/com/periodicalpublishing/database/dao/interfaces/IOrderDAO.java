package com.periodicalpublishing.database.dao.interfaces;

import com.periodicalpublishing.database.entities.Order;

import java.util.List;

public interface IOrderDAO {
    List<Order> findAll();
    Order findById(long id);
    List<Order> findByUserId(long userId);
    Order save(Order order);
    Order update(Order order);
    void delete(long id);
}
