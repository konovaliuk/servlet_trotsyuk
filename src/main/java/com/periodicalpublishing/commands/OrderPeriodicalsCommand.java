package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.services.OrderService;
import com.periodicalpublishing.services.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderPeriodicalsCommand extends BaseCommand{
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        Order order = orderService.getById(orderId);
        PeriodicalService periodicalService = new PeriodicalService();
        List<Periodical> periodicals = periodicalService.getByOrderId(orderId);
        HttpSession session = request.getSession();
        session.setAttribute("editPeriodical", null);
        session.setAttribute("orderPeriodicals", periodicals);
        session.setAttribute("order", order);
        return ORDER_PERIODICALS_PAGE;
    }
}