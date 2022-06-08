package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RemovePeriodicalFromOrderCommand extends BaseCommand {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        Long periodicalId = Long.valueOf(request.getParameter("periodicalId"));
        orderService.removePeriodicalFromOrder(order.getId(), periodicalId);

        List<Periodical> orderPeriodicals = (List<Periodical>) session.getAttribute("orderPeriodicals");
        for(int i = 0; i < orderPeriodicals.size(); i++) {
            if(orderPeriodicals.get(i).getId() == periodicalId) {
                orderPeriodicals.remove(orderPeriodicals.get(i));
            }
        }
        session.setAttribute("orderPeriodicals", orderPeriodicals);
        if(orderPeriodicals.size() == 0) {
            orderService.deleteOrder(order.getId());
            List<Order> orders = orderService.getByUserId((Long) session.getAttribute("userId"));
            session.setAttribute("orders", orders);
            return USER_ORDERS_PAGE;
        }

        List<Order> orders = orderService.getByUserId((Long) session.getAttribute("userId"));
        session.setAttribute("orders", orders);
        return ORDER_PERIODICALS_PAGE;
    }
}