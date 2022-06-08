package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteOrderCommand extends BaseCommand {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        orderService.deleteOrder(orderId);
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null)
            return USER_ORDERS_PAGE;
        List<Order> orders = orderService.getByUserId((Long) session.getAttribute("userId"));
        session.setAttribute("orders", orders);
        return USER_ORDERS_PAGE;
    }
}