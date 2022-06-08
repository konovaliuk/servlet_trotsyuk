package com.periodicalpublishing.database.dao.interfaces;

import com.periodicalpublishing.database.entities.OrderPeriodical;

import java.util.List;

public interface IOrderPeriodicalDAO {
    List<OrderPeriodical> findAll();
    List<OrderPeriodical> findByOrderId(long order_id);
    OrderPeriodical findOne(long order_id, long periodical_id);
    OrderPeriodical save(OrderPeriodical orderPeriodical);
    void delete(long order_id, long periodical_id);
}
