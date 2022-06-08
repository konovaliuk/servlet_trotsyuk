package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.database.entities.enums.Status;
import com.periodicalpublishing.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class CreateOrderCommand extends BaseCommand {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] oprderPeriodicals = request.getParameterValues("periodicals");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Order order = orderService.addOrder(userId, Status.NOT_PAID, oprderPeriodicals);
        if (order == null) {
            session.setAttribute("errorMessage", "Сould not create the order, please try again");
            return PERIODICALS_PAGE;
        }
        session.setAttribute("errorMessage", null);
        List<Order> orders = orderService.getByUserId((Long) session.getAttribute("userId"));
        session.setAttribute("orders", orders);
        return USER_ORDERS_PAGE;
    }
}