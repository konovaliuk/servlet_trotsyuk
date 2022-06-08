package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.database.entities.OrderPeriodical;
import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.services.OrderService;
import com.periodicalpublishing.services.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddPeriodicalsToOrderCommand extends BaseCommand{
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] orderPeriodicals = request.getParameterValues("periodicals");
        HttpSession session = request.getSession();
        if(orderPeriodicals.length == 0) {
            session.setAttribute("editPeriodical", null);
            return ORDER_PERIODICALS_PAGE;
        }
        Order order = (Order) session.getAttribute("order");
        orderService.addPeriodicalsToOrder(order.getId(), orderPeriodicals);
        PeriodicalService periodicalService = new PeriodicalService();
        List<Periodical> periodicals = periodicalService.getByOrderId(order.getId());
        session.setAttribute("editPeriodical", null);
        session.setAttribute("orderPeriodicals", periodicals);
        return ORDER_PERIODICALS_PAGE;
    }
}