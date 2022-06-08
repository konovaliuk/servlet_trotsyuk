package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Subscription;
import com.periodicalpublishing.services.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SubscriptionsCommand extends BaseCommand{
    private static final SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null)
            return SUBSCRIPTIONS_PAGE;
        Long userId = (Long) session.getAttribute("userId");
        List<Subscription> subscriptions = subscriptionService.getByUserId(userId);
        request.setAttribute("subscriptions", subscriptions);
        return SUBSCRIPTIONS_PAGE;
    }
}
