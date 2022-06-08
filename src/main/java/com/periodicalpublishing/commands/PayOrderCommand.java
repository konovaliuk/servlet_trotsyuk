package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PayOrderCommand extends BaseCommand {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        Order paidOrder = orderService.payOrder(orderId);
        HttpSession session = request.getSession();
        if (paidOrder == null) {
            session.setAttribute("errorMessage", "Failed to pay for the order, please try again");
            return USER_ORDERS_PAGE;
        }
        List<Order> orders = orderService.getByUserId((Long) session.getAttribute("userId"));
        session.setAttribute("orders", orders);
        session.setAttribute("errorMessage", null);
        return USER_ORDERS_PAGE;
    }
}