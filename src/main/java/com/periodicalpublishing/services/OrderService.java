package com.periodicalpublishing.services;

import com.periodicalpublishing.database.dao.DAOFactory;
import com.periodicalpublishing.database.dao.interfaces.IOrderDAO;
import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.database.entities.OrderPeriodical;
import com.periodicalpublishing.database.entities.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class OrderService {
    private static final IOrderDAO orderDAO = DAOFactory.getOrderDAO();

    public List<Order> getAll() {
        List<Order> orderList = orderDAO.findAll();
        for(Order order : orderList) {
            List<OrderPeriodical> orderPeriodicals = DAOFactory.getOrderPeriodicalDAO().findByOrderId(order.getId());
            double totalPrice = 0;
            for(OrderPeriodical orderPeriodical : orderPeriodicals) {
                totalPrice += DAOFactory.getPeriodicalDAO().findById(orderPeriodical.getPeriodicalId()).getPrice();
            }
            order.setTotalPrice(totalPrice);
        }
        return orderList;
    }

    public Order getById(long id) {
        return orderDAO.findById(id);
    }

    public List<Order> getByUserId(long userId) {
        List<Order> orderList = orderDAO.findByUserId(userId);
        if(orderList == null)
            return null;
        for(Order order : orderList) {
            List<OrderPeriodical> orderPeriodicals = DAOFactory.getOrderPeriodicalDAO().findByOrderId(order.getId());
            double totalPrice = 0;
            for(OrderPeriodical orderPeriodical : orderPeriodicals) {
                totalPrice += DAOFactory.getPeriodicalDAO().findById(orderPeriodical.getPeriodicalId()).getPrice();
            }
            order.setTotalPrice(totalPrice);
        }
        return orderList;
    }

    public Order addOrder(long userId, Status status, String[] orderPeriodicals) {
        Order order = new Order(null, userId, LocalDate.now(), status);
        order = orderDAO.save(order);
        OrderPeriodical orderPeriodical;
        for(String periodicalId : orderPeriodicals) {
            orderPeriodical = new OrderPeriodical(order.getId(), Long.valueOf(periodicalId));
            DAOFactory.getOrderPeriodicalDAO().save(orderPeriodical);
        }
        return order;
    }

    public Order payOrder(long id) {
        Order orderToPay = orderDAO.findById(id);
        orderToPay.setStatus(Status.PAID);
        orderToPay = orderDAO.update(orderToPay);
        List<OrderPeriodical> orderPeriodicals = DAOFactory.getOrderPeriodicalDAO().findByOrderId(orderToPay.getId());
        SubscriptionService subscriptionService = new SubscriptionService();
        for(OrderPeriodical orderPeriodical : orderPeriodicals) {
            subscriptionService.addSubscription(orderToPay.getUserId(), orderPeriodical.getPeriodicalId());
        }
        return orderToPay;
    }

    public void addPeriodicalsToOrder(long orderId, String[] orderPeriodicals) {
        OrderPeriodical orderPeriodical;
        for(String periodicalId : orderPeriodicals) {
            orderPeriodical = new OrderPeriodical(orderId, Long.valueOf(periodicalId));
            DAOFactory.getOrderPeriodicalDAO().save(orderPeriodical);
        }
    }

    public void removePeriodicalFromOrder(long orderId, long periodicalId) {
        DAOFactory.getOrderPeriodicalDAO().delete(orderId, periodicalId);
    }

    public void deleteOrder(long id) {
        orderDAO.delete(id);
    }
}
