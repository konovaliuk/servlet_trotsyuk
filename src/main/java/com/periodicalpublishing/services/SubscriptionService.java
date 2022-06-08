package com.periodicalpublishing.services;

import com.periodicalpublishing.database.dao.DAOFactory;
import com.periodicalpublishing.database.dao.interfaces.ISubscriptionDAO;
import com.periodicalpublishing.database.entities.Subscription;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {
    private static final ISubscriptionDAO subscriptionDAO = DAOFactory.getSubscriptionDAO();

    public List<Subscription> getAll() {
        List<Subscription> subscriptions = subscriptionDAO.findAll();
        for(Subscription subscription : subscriptions) {
            subscription.setPeriodical(DAOFactory.getPeriodicalDAO().findById(subscription.getPeriodicalId()));
            if(subscription.getDateEnd().isBefore(LocalDate.now())) {
                subscription.setIsActive(false);
            } else {
                subscription.setIsActive(true);
            }
        }
        return subscriptions;
    }

    public List<Subscription> getByUserId(long user_id) {
        List<Subscription> subscriptions = subscriptionDAO.findByUserId(user_id);
        for(Subscription subscription : subscriptions) {
            subscription.setPeriodical(DAOFactory.getPeriodicalDAO().findById(subscription.getPeriodicalId()));
            if(subscription.getDateEnd().isBefore(LocalDate.now())) {
                subscription.setIsActive(false);
            } else {
                subscription.setIsActive(true);
            }
        }
        return subscriptions;
    }

    public Subscription addSubscription(Long userId, Long periodicalId) {
        LocalDate dateStart = LocalDate.now();
        Subscription lastSubscription = subscriptionDAO.findLastByUserAndPeriodical(userId, periodicalId);
        if(lastSubscription != null) {
            LocalDate lastSubscriptionDateEnd = lastSubscription.getDateEnd();
            if(dateStart.isBefore(lastSubscriptionDateEnd)) {
                dateStart = lastSubscriptionDateEnd;
            }
        }
        Subscription subscription = new Subscription(null, userId, periodicalId, dateStart, dateStart.plusMonths(1));
        return subscriptionDAO.save(subscription);
    }
}
