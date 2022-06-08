package com.periodicalpublishing.database.dao.interfaces;

import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.database.entities.Subscription;

import java.util.List;

public interface ISubscriptionDAO {
    List<Subscription> findAll();
    Subscription findById(long id);
    List<Subscription> findByUserId(long userId);
    Subscription findLastByUserAndPeriodical(long userId, long subscriptionId);
    Subscription save(Subscription subscription);
    Subscription update(Subscription subscription);
    void delete(long id);
}
